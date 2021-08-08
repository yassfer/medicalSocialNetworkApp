package com.health.talan.controller;


import com.health.talan.entities.Challenge;
import com.health.talan.service.ChallengeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("challenge")
public class ChallengeController {

    private final ChallengeServiceImpl challengeServiceImpl;

    @Autowired
    public ChallengeController(ChallengeServiceImpl challengeServiceImpl){

        this.challengeServiceImpl = challengeServiceImpl;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Challenge>> getAllChallenges(){
        List<Challenge> challenges = challengeServiceImpl.getAllChallenges();
        return new ResponseEntity<>(challenges, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Challenge>> getChallengeById(@PathVariable("id") Long id){
        Optional<Challenge> challenge = challengeServiceImpl.getChallengeById(id);
        return new ResponseEntity<>(challenge, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Challenge> publishChallenge(@RequestBody Challenge challenge){
        Challenge newChallenge = challengeServiceImpl.SaveChallenge(challenge);
        return new ResponseEntity<>(newChallenge, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteChallenge(@PathVariable("id") Long id){
        String message = challengeServiceImpl.deleteChallenge(id);
        if (message.equals("Deleted")){
            return new ResponseEntity<>(message,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
        }
    }

}
