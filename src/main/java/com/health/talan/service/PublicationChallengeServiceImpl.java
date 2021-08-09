package com.health.talan.service;

import com.health.talan.Repository.PublicationChallengeRepo;
import com.health.talan.entities.Challenge;
import com.health.talan.entities.PublicationChallenge;
import com.health.talan.entities.User;
import com.health.talan.service.serviceInterfaces.PublicationChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class PublicationChallengeServiceImpl implements PublicationChallengeService {

    private final PublicationChallengeRepo publicationChallengeRepo;
    private final ChallengeServiceImpl challengeServiceImpl;
    private final UserServiceImpl userServiceImpl;

    @Autowired
    public PublicationChallengeServiceImpl(ChallengeServiceImpl challengeServiceImpl, PublicationChallengeRepo publicationChallengeRepo, UserServiceImpl userServiceImpl){

        this.publicationChallengeRepo = publicationChallengeRepo;
        this.challengeServiceImpl = challengeServiceImpl;
        this.userServiceImpl = userServiceImpl;
    }

    @Override
    public List<PublicationChallenge> getAllChallengePublications() {

        return publicationChallengeRepo.findAll();
    }


    @Override
    public Optional<PublicationChallenge> getPublicationChallengeById(Long id){

        return Optional.ofNullable(publicationChallengeRepo.findById(id)).orElse(null);
    }


    @Override
    public Optional<List<PublicationChallenge>> getPublicationChallengeByUserId(Long userId){

        return Optional.ofNullable(publicationChallengeRepo.findByUserId(userId)).orElse(null);
    }


    @Override
    public Optional<List<PublicationChallenge>> getPublicationChallengeByApprouved(Boolean approuved, Long challengeId){

        return Optional.ofNullable(publicationChallengeRepo.findByApprouved(approuved, challengeId)).orElse(null);
    }


    @Override
    public Optional<List<PublicationChallenge>> getPublicationChallengeByChallengeId(Long challengeId){

        return Optional.ofNullable(publicationChallengeRepo.findByChallengeId(challengeId)).orElse(null);
    }



    @Override
    public PublicationChallenge addPublicationChallenge(PublicationChallenge publicationChallenge, Long challengeId, Long userId){

            Optional<Challenge> challenge = challengeServiceImpl.getChallengeById(challengeId);
            Optional<User> user = userServiceImpl.getUserById(userId);

            publicationChallenge.setChallenge(challenge.get());
            publicationChallenge.setUser(user.get());

            PublicationChallenge newPublicationChallenge = publicationChallengeRepo.save(publicationChallenge);

            return newPublicationChallenge;

        }



    @Override
    public Optional<PublicationChallenge> getPublicationChallengeByChallengeAndUser(Long userId, Long challengeId){

        Optional<PublicationChallenge> newPublicationChallenge = publicationChallengeRepo.findByChallengeAndUser(userId,challengeId);

        return newPublicationChallenge;
    }



    public PublicationChallenge updatePublicationChallenge(PublicationChallenge publicationChallenge){

        PublicationChallenge newPublicationChallenge = publicationChallengeRepo.save(publicationChallenge);

        return newPublicationChallenge;
    }


    @Override
    public String deleteChallengePublicationsByUserId(Long userId){

        Optional<List<PublicationChallenge>> publicationChallenges = getPublicationChallengeByUserId(userId);
        if(publicationChallenges.isPresent()){

            for(PublicationChallenge pub : publicationChallenges.get()){

                publicationChallengeRepo.deleteById(pub.getId());
            }

            return "Challenge Publication Deleted";
        }
        else {
            return "Challenge Publication doesn't exit";
        }
    }



    @Override
    public String deleteChallengePublicationsByChallengeId(Long challengeId){

        Optional<List<PublicationChallenge>> publicationChallenges = getPublicationChallengeByChallengeId(challengeId);
        if(publicationChallenges.isPresent()){

            for(PublicationChallenge pub : publicationChallenges.get()){

                publicationChallengeRepo.deleteById(pub.getId());
            }

            return "Challenge Publication Deleted";
        }
        else {
            return "Challenge Publication doesn't exit";
        }
    }


    @Override
    public String deletePublicationChallengeByChallengeAndUser(Long userId, Long challengeId){

        Optional<PublicationChallenge> publicationChallenge = getPublicationChallengeByChallengeAndUser(userId, challengeId);

        if(publicationChallenge.isPresent()){
            publicationChallengeRepo.delete(publicationChallenge.get());
            return "Challenge Publication Deleted";
        }
        else {
            return "Challenge Publication doesn't exit";
        }
    }



    public String deletePublicationChallenge(Long id){
        Optional<PublicationChallenge> publicationChallenge = publicationChallengeRepo.findById(id);
        if(publicationChallenge.isPresent()){
            publicationChallengeRepo.deleteById(id);
            return "Challenge Publication Deleted";
        }
        else {
            return "Challenge Publication doesn't exit";
        }
    }



}
