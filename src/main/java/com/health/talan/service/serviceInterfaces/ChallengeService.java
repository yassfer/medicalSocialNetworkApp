package com.health.talan.service.serviceInterfaces;

import com.health.talan.entities.Challenge;

import java.util.List;
import java.util.Optional;

public interface ChallengeService {

    public List<Challenge> getAllChallenges();

    public Optional<Challenge> getChallengeById(Long id);

    public Challenge SaveChallenge(Challenge challenge);

    public String deleteChallenge(Long id);
}
