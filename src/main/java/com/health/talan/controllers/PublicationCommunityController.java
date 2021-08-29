package com.health.talan.controllers;

import com.health.talan.entities.PieceJoint;
import com.health.talan.entities.PublicationCommunity;
import com.health.talan.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/publicationCommunity")
public class PublicationCommunityController {

    private final PublicationCommunityServiceImpl publicationCommunityServiceImpl;
    private final CommunityServiceImplémentation communityServiceImplémentation;

    @Autowired
    public PublicationCommunityController(PublicationCommunityServiceImpl publicationCommunityServiceImpl, CommunityServiceImplémentation communityServiceImplémentation) {

        this.publicationCommunityServiceImpl = publicationCommunityServiceImpl;
        this.communityServiceImplémentation = communityServiceImplémentation;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PublicationCommunity>> getAllPublications() {

        List<PublicationCommunity> publications = publicationCommunityServiceImpl.getAllPublications();
        return new ResponseEntity<>(publications, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPublicationById(@PathVariable("id") Long id) {
        Optional<PublicationCommunity> publication = publicationCommunityServiceImpl.getPublicationById(id);
        if (publication.isPresent()) {
            return new ResponseEntity<>(publication, HttpStatus.OK);
        }

        return new ResponseEntity<>("publication not found", HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getAllPublicationsByUserId(@PathVariable("userId") Long userId) {

        Optional<List<PublicationCommunity>> publications = publicationCommunityServiceImpl.getPublicationByUserId(userId);
        if (publications.isPresent()) {
            for (PublicationCommunity publication : publications.get()) {
                for (PieceJoint pieceJoint : publication.getPieceJoints()) {
                    pieceJoint.setData(publicationCommunityServiceImpl.decompressBytes(pieceJoint.getData()));
                }
            }
            return new ResponseEntity<>(publications, HttpStatus.OK);
        }
        return new ResponseEntity<>("that user have no publications", HttpStatus.OK);
    }



    @GetMapping("/community/{communityId}")
    public ResponseEntity<?> getAllPublicationsByCommunityId(@PathVariable("communityId") Long communityId) {

        Optional<List<PublicationCommunity>> publications = publicationCommunityServiceImpl.getPublicationCommunityByCommunityId(communityId);
        if (publications.isPresent()) {
            for (PublicationCommunity publication : publications.get()) {
                for (PieceJoint pieceJoint : publication.getPieceJoints()) {
                    pieceJoint.setData(publicationCommunityServiceImpl.decompressBytes(pieceJoint.getData()));
                }
            }
            return new ResponseEntity<>(publications, HttpStatus.OK);
        }
        return new ResponseEntity<>("that community have no publications", HttpStatus.OK);
    }


    @PostMapping("/{communityId}/{userId}")
    public ResponseEntity<PublicationCommunity> AddPublicationCommunity(
            @RequestBody PublicationCommunity publicationCommunity, @PathVariable("userId") Long userId,
            @PathVariable("communityId") Long communityId) throws IOException {

        PublicationCommunity newPublicationCommunity = publicationCommunityServiceImpl
                .addPublicationCommunity(publicationCommunity, communityId, userId);
        return new ResponseEntity<>(newPublicationCommunity, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePublication(@PathVariable("id") Long id) {

        String message = publicationCommunityServiceImpl.deletePublication(id);
        if (message.equals("Publication Deleted")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllByDate")
    @ResponseBody
    public List<PublicationCommunity> getAllPublicationsByData() {

        List<PublicationCommunity> publications = publicationCommunityServiceImpl.getAllByDate();

        for (PublicationCommunity publication : publications) {
            for (PieceJoint pieceJoint : publication.getPieceJoints()) {
                pieceJoint.setData(publicationCommunityServiceImpl.decompressBytes(pieceJoint.getData()));
            }
        }
        return publications;
    }
}
