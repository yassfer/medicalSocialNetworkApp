package com.health.talan.controller;

import com.health.talan.entities.PieceJoint;
import com.health.talan.service.PieceJointServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


@CrossOrigin(origins = "http://localhost:4200")
@Controller
@RequestMapping("api/pieceJoint")
public class PieceJointController {


    private PieceJointServiceImpl pieceJointServiceImpl;



    @Autowired
    public PieceJointController(PieceJointServiceImpl pieceJointServiceImpl) {

        this.pieceJointServiceImpl = pieceJointServiceImpl;
    }



    @PostMapping("/upload/publication/{publicationId}")
    public ResponseEntity<?> uploadPieceJoint(@RequestParam("pieceJoint") MultipartFile pieceJoint, @PathVariable("publicationId")Long publicationId) {

        try {
            PieceJoint pieceJoint1 = pieceJointServiceImpl.store(pieceJoint, publicationId);
            String pieceJointDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/pieceJoint/")
                    .path(pieceJoint1.getId().toString())
                    .toUriString();
            pieceJoint1.setUrl(pieceJointDownloadUri);

            pieceJointServiceImpl.updatePieceJoint(pieceJoint1);
            return ResponseEntity.ok().body(pieceJoint1);

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body("Could not upload the pieceJoint: " + pieceJoint.getOriginalFilename() + "!");
        }
    }



    @PostMapping("/uploadPieceJoints/publication/{publicationId}")
    public ResponseEntity<?> uploadFiles(@RequestParam("pieceJoints") MultipartFile[] pieceJoints, @PathVariable("publicationId")Long publicationId) {

        try {
            List<PieceJoint> pieces = new ArrayList<>();

            Arrays.asList(pieceJoints).stream().forEach(pieceJoint -> {
                try {
                    PieceJoint pieceJoint1 = pieceJointServiceImpl.store(pieceJoint, publicationId);

                    String pieceJointDownloadUri = ServletUriComponentsBuilder
                            .fromCurrentContextPath()
                            .path("/api/pieceJoint/")
                            .path(pieceJoint1.getId().toString())
                            .toUriString();
                    pieceJoint1.setUrl(pieceJointDownloadUri);

                    pieceJointServiceImpl.updatePieceJoint(pieceJoint1);
                    pieces.add(pieceJoint1);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            });

            return ResponseEntity.status(HttpStatus.OK).body(pieces);
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Fail to upload pieceJoints!");
        }
    }



   @GetMapping("/all")
    public ResponseEntity<List<PieceJoint>> getListPieceJoints() {
        List<PieceJoint> pieceJoints = pieceJointServiceImpl.getAllPieceJoints().collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(pieceJoints);
    }


    @GetMapping("/publication/{publicationId}")
    public ResponseEntity<?> getListPieceJoints(@PathVariable("publicationId")Long publicationId) {
        Optional<List<PieceJoint>> pieceJoints = pieceJointServiceImpl.getAllPieceJointsByPubId(publicationId);

        if(pieceJoints.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(pieceJoints.get());
        } else{
            return ResponseEntity.status(HttpStatus.OK).body("This publication have no pieceJoints");
        }

    }




    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getPieceJoint(@PathVariable Long id) {
        Optional<PieceJoint> pieceJoint = pieceJointServiceImpl.getPieceJoint(id);

        if (!pieceJoint.isPresent()) {
            return ResponseEntity.notFound()
                    .build();
        }

        PieceJoint pieceJoint1 = pieceJoint.get();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + pieceJoint1.getName() + "\"")
                .contentType(MediaType.valueOf(pieceJoint1.getContentType()))
                .body(pieceJoint1.getData());
    }


}

