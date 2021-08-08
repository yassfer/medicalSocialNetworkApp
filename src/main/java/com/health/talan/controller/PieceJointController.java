package com.health.talan.controller;

import com.health.talan.Response.ResponseMessage;
import com.health.talan.Response.ResponsePieceJoint;
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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/pieceJoint")
public class PieceJointController {


    private PieceJointServiceImpl pieceJointServiceImpl;



    @Autowired
    public PieceJointController(PieceJointServiceImpl pieceJointServiceImpl) {

        this.pieceJointServiceImpl = pieceJointServiceImpl;
    }



    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("pieceJoint") MultipartFile pieceJoint) {
        String message = "";
        try {
            pieceJointServiceImpl.store(pieceJoint);

            message = "Uploaded the pieceJoint successfully: " + pieceJoint.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the pieceJoint: " + pieceJoint.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }



    @PostMapping("/uploadMultiplePieceJoints")
    public List<ResponseEntity<ResponseMessage>> uploadMultiplePieceJoints(@RequestParam("pieceJoints") MultipartFile[] pieceJoints) {
        return Arrays.stream(pieceJoints)
                .map(pieceJoint -> uploadFile(pieceJoint))
                .collect(Collectors.toList());
    }





   @GetMapping("/all")
    public ResponseEntity<List<ResponsePieceJoint>> getListPieceJoints() {
        List<ResponsePieceJoint> pieceJoints = pieceJointServiceImpl.getAllPieceJoints().map(pieceJoint -> {
            String pieceJointDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/pieceJoints/")
                    .path(pieceJoint.getId().toString())
                    .toUriString();

            return new ResponsePieceJoint(
                    pieceJoint.getId(),
                    pieceJoint.getName(),
                    pieceJointDownloadUri,
                    pieceJoint.getContentType(),
                    pieceJoint.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(pieceJoints);
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




   /*
   @PostMapping("/uploadMultiplePieceJoints")
    public List<ResponsePieceJoint> uploadMultiplePieceJoints(@RequestParam("pieceJoints") MultipartFile[] pieceJoints) {

        List<ResponsePieceJoint> pieceJoints1 = new ArrayList<>();
        try {
            for(MultipartFile pj : pieceJoints){

                PieceJoint pieceJoint = pieceJointService.store(pj);
                pieceJoints1.add(mapToPieceJointResponse(pieceJoint));
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return pieceJoints1;
    }



    private ResponsePieceJoint mapToPieceJointResponse(PieceJoint pieceJoint) {
        String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/pieceJoints/")
                .path(String.valueOf(pieceJoint.getId()))
                .toUriString();
        ResponsePieceJoint responsePieceJoint = new ResponsePieceJoint();
        responsePieceJoint.setId(String.valueOf(pieceJoint.getId()));
        responsePieceJoint.setName(pieceJoint.getName());
        responsePieceJoint.setContentType(pieceJoint.getContentType());
        responsePieceJoint.setSize(Math.toIntExact(pieceJoint.getSize()));
        responsePieceJoint.setUrl(downloadURL);

        return responsePieceJoint;
    }*/


}

