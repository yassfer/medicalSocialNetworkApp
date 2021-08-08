package com.health.talan.controller;

import com.health.talan.entities.PublicationChallenge;
import com.health.talan.service.PublicationChallengeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/publicationChallenge")
public class PublicationChallengeController {

    private final PublicationChallengeServiceImpl publicationChallengeServiceImpl;


    @Autowired
    public PublicationChallengeController(PublicationChallengeServiceImpl publicationChallengeServiceImpl) {

        this.publicationChallengeServiceImpl = publicationChallengeServiceImpl;
    }


    @GetMapping("/all")
    public ResponseEntity<List<PublicationChallenge>> getAllChallengePublications() {

        List<PublicationChallenge> ChallengePublications = publicationChallengeServiceImpl.getAllChallengePublications();
        return new ResponseEntity<>(ChallengePublications, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<PublicationChallenge>> getPublicationChallengeById(@PathVariable("id") Long id) {
        Optional<PublicationChallenge> ChallengePublication = publicationChallengeServiceImpl.getPublicationChallengeById(id);
        if (ChallengePublication.isPresent()) {
            return new ResponseEntity<>(ChallengePublication, HttpStatus.OK);
        }
        throw new IllegalStateException("ChallengePublication not found");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getAllPublicationsByUserId(@PathVariable("userId") Long userId) {

        Optional<List<PublicationChallenge>> Challengepublications = publicationChallengeServiceImpl.getPublicationChallengeByUserId(userId);
        if (Challengepublications.isPresent()) {
            return new ResponseEntity<>(Challengepublications, HttpStatus.OK);
        }
        return new ResponseEntity<>("that user have no challenges", HttpStatus.OK);
    }


    @GetMapping("/challenge/{challengeId}")
    public ResponseEntity<?> getAllPublicationsByChallengeId(@PathVariable("challengeId") Long challengeId) {

        Optional<List<PublicationChallenge>> Challengepublications = publicationChallengeServiceImpl.getPublicationChallengeByChallengeId(challengeId);
        if (Challengepublications.isPresent()) {
            return new ResponseEntity<>(Challengepublications, HttpStatus.OK);
        }
        return new ResponseEntity<>("that challenge have no challengePublications", HttpStatus.OK);
    }


    @GetMapping("/approuved/challenge/{challengeId}")
    public ResponseEntity<?> getAllApprouvedChallengePublications(@PathVariable("challengeId") Long challengeId) {

        Optional<List<PublicationChallenge>> Challengepublications = publicationChallengeServiceImpl.getPublicationChallengeByApprouved(true,challengeId);
        if (Challengepublications.isPresent()) {
            return new ResponseEntity<>(Challengepublications, HttpStatus.OK);
        }
        return new ResponseEntity<>("that challenge have no challengePublications", HttpStatus.OK);
    }



    @PostMapping()
    public ResponseEntity<PublicationChallenge> AddPublicationChallenge(@RequestBody PublicationChallenge publicationChallenge) {

        PublicationChallenge newPublicationChallenge = publicationChallengeServiceImpl.addPublicationChallenge(publicationChallenge);
        return new ResponseEntity<>(newPublicationChallenge, HttpStatus.OK);
    }


    @GetMapping("/approuve/{challengePublicationId}")
    public ResponseEntity<?> approuvePublicationChallenge(@PathVariable("challengePublicationId") Long id) {

        Optional<PublicationChallenge> pub = publicationChallengeServiceImpl.getPublicationChallengeById(id);
        if (pub.isPresent()) {
            pub.get().setApprouved(true);
            return new ResponseEntity<>(pub, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Challenge publication doesn't exist", HttpStatus.BAD_REQUEST);
        }

    }



    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePublicationChallenge(@PathVariable("id") Long id, @RequestBody PublicationChallenge publicationChallenge) {

        Optional<PublicationChallenge> pub = publicationChallengeServiceImpl.getPublicationChallengeById(id);
        if (pub.isPresent()) {
            publicationChallengeServiceImpl.updatePublicationChallenge(publicationChallenge);
            return new ResponseEntity<>(pub, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Challenge doesn't exist", HttpStatus.BAD_REQUEST);
        }

    }



    @DeleteMapping("/user/{userId}")
    public ResponseEntity<?> deleteChallengePublicationsByUserId(@PathVariable("userId") Long userId) {

        String message = publicationChallengeServiceImpl.deleteChallengePublicationsByUserId(userId);
        if (message.equals("Challenge Publication Deleted")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }




    @DeleteMapping("/user/{userId}/challenge/{challengeId}")
    public ResponseEntity<?> deletePublicationChallengeByUserAndChallenge(@PathVariable("userId") Long userId, @PathVariable("challengeId") Long challengeId) {

        String message = publicationChallengeServiceImpl.deletePublicationChallengeByChallengeAndUser(userId, challengeId);
        if (message.equals("Challenge Publication Deleted")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }




    //refuse or simple delete
    @DeleteMapping("/{challengePublicationId}")
    public ResponseEntity<?> deletePublicationChallenge(@PathVariable("challengePublicationId") Long id) {

        String message = publicationChallengeServiceImpl.deletePublicationChallenge(id);
        if (message.equals("Challenge Publication Deleted")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }
}
