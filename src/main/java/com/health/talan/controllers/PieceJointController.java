package com.health.talan.controllers;

import com.health.talan.entities.PieceJoint;
import com.health.talan.entities.Publication;
import com.health.talan.services.PieceJointServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/pieceJoint")
public class PieceJointController {


    private PieceJointServiceImpl pieceJointServiceImpl;



    @Autowired
    public PieceJointController(PieceJointServiceImpl pieceJointServiceImpl) {

        this.pieceJointServiceImpl = pieceJointServiceImpl;
    }


    @PostMapping(value= "/upload/user/{userEmail}", consumes = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> uploadPieceJustif(@RequestParam("pieceJustif") MultipartFile pieceJoint, @PathVariable("userEmail")String userEmail) {

        try {
            PieceJoint pieceJoint1 = pieceJointServiceImpl.storePieceJustif(pieceJoint, userEmail);
            String pieceJointDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/pieceJoint/")
                    .path(pieceJoint1.getId().toString())
                    .toUriString();
            pieceJoint1.setUrl(pieceJointDownloadUri);

            pieceJointServiceImpl.updatePieceJoint2(pieceJoint1);
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

                    pieceJointServiceImpl.updatePieceJoint(pieceJoint1, publicationId);
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



    @PostMapping("/uploadPieceJoints")
    public ResponseEntity<?> uploadFilesWithoutPubId(@RequestParam("pieceJoints") MultipartFile[] pieceJoints) {

        try {
            List<PieceJoint> pieces = new ArrayList<>();

            Arrays.asList(pieceJoints).stream().forEach(pieceJoint -> {
                try {
                    PieceJoint pieceJoint1 = pieceJointServiceImpl.store2(pieceJoint);

                    String pieceJointDownloadUri = ServletUriComponentsBuilder
                            .fromCurrentContextPath()
                            .path("/api/pieceJoint/")
                            .path(pieceJoint1.getId().toString())
                            .toUriString();
                    pieceJoint1.setUrl(pieceJointDownloadUri);

                    pieceJointServiceImpl.updatePieceJoint2(pieceJoint1);
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
    public ResponseEntity<?> getPieceJoint(@PathVariable Long id) {
        Optional<PieceJoint> pieceJoint = pieceJointServiceImpl.getPieceJoint(id);

        if (pieceJoint.isPresent()) {
            return new ResponseEntity<>(pieceJoint, HttpStatus.OK);
        }

        return new ResponseEntity<>("publication not found", HttpStatus.OK);

        /*if (!pieceJoint.isPresent()) {
            return ResponseEntity.notFound()
                    .build();
        }

        PieceJoint pieceJoint1 = pieceJoint.get();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + pieceJoint1.getName() + "\"")
                .contentType(MediaType.valueOf(pieceJoint1.getContentType()))
                .body(pieceJoint1.getData());*/
    }

    

    @PatchMapping("/publication/{publicationId}")
    public ResponseEntity<?> updatePublication(@PathVariable("publicationId") Long publicationId, @RequestBody PieceJoint[] pieceJoints) {
        for(PieceJoint pieceJoint : pieceJoints){
            Optional<PieceJoint> updatedPieceJoint = pieceJointServiceImpl.getPieceJoint(pieceJoint.getId());
            if (updatedPieceJoint.isPresent()) {
                pieceJointServiceImpl.updatePieceJoint(updatedPieceJoint.get(), publicationId);
            }
            else {
                return new ResponseEntity<>("update failed", HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<>("update done successfully", HttpStatus.OK);
    }


    @PatchMapping("/publicationChallenge/{publicationChallengeId}")
    public ResponseEntity<?> updatePublicationChallenge(@PathVariable("publicationChallengeId") Long publicationChallengeId, @RequestBody PieceJoint[] pieceJoints) {
        for(PieceJoint pieceJoint : pieceJoints){
            Optional<PieceJoint> updatedPieceJoint = pieceJointServiceImpl.getPieceJoint(pieceJoint.getId());
            if (updatedPieceJoint.isPresent()) {
                pieceJointServiceImpl.updatePieceJointChallenge(updatedPieceJoint.get(), publicationChallengeId);
            }
            else {
                return new ResponseEntity<>("update failed", HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<>("update done successfully", HttpStatus.OK);
    }


    @PatchMapping("/publicationCommunity/{publicationCommunity}")
    public ResponseEntity<?> updatePublicationCommunity(@PathVariable("publicationCommunity") Long publicationCommunityId, @RequestBody PieceJoint[] pieceJoints) {
        for(PieceJoint pieceJoint : pieceJoints){
            Optional<PieceJoint> updatedPieceJoint = pieceJointServiceImpl.getPieceJoint(pieceJoint.getId());
            if (updatedPieceJoint.isPresent()) {
                pieceJointServiceImpl.updatePieceJointCommunity(updatedPieceJoint.get(), publicationCommunityId);
            }
            else {
                return new ResponseEntity<>("update failed", HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<>("update done successfully", HttpStatus.OK);
    }

}


