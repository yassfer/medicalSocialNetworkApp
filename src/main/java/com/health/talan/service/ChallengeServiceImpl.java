package com.health.talan.service;

import com.health.talan.Repository.ChallengeRepo;
import com.health.talan.entities.Challenge;
import com.health.talan.service.serviceInterfaces.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChallengeServiceImpl implements ChallengeService {

    private final ChallengeRepo challengeRepo;

    @Autowired
    public ChallengeServiceImpl(ChallengeRepo challengeRepo){

        this.challengeRepo = challengeRepo;
    }

    @Override
    public List<Challenge> getAllChallenges() {

        return challengeRepo.findAll();
    }

    @Override
    public Optional<Challenge> getChallengeById(Long id){

        return Optional.ofNullable(challengeRepo.findById(id).orElse(null));
    }

    @Override
    public Challenge SaveChallenge(Challenge challenge){

        return challengeRepo.save(challenge);
    }

    @Override
    public String deleteChallenge(Long id){
        Optional<Challenge> challenge = challengeRepo.findById(id);
        if(challenge.isPresent()){
            challengeRepo.deleteById(id);
            return "Challenge Deleted";
        }
        else {
            return "Challenge doesn't exit";
        }
    }
}
