package com.health.talan.services.serviceInterfaces;

import com.health.talan.entities.PublicationChallenge;

import java.util.List;
import java.util.Optional;

public interface PublicationChallengeService {

    public List<PublicationChallenge> getAllChallengePublications();

    public Optional<PublicationChallenge> getPublicationChallengeById(Long id);

    public Optional<List<PublicationChallenge>> getPublicationChallengeByUserId(Long userId);

    public Optional<List<PublicationChallenge>> getPublicationChallengeByApprouved(Boolean approuved, Long challengeId);

    public Optional<List<PublicationChallenge>> getPublicationChallengeByChallengeId(Long challengeId);

    public PublicationChallenge addPublicationChallenge(PublicationChallenge publicationChallenge, Long challengeId, Long userId);

    public Optional<PublicationChallenge> getPublicationChallengeByChallengeAndUser(Long userId, Long challengeId);

    public PublicationChallenge updatePublicationChallenge(PublicationChallenge publicationChallenge);

    public String deleteChallengePublicationsByUserId(Long userId);

    public String deleteChallengePublicationsByChallengeId(Long challengeId);

    public String deletePublicationChallengeByChallengeAndUser(Long userId, Long challengeId);

    public String deletePublicationChallenge(Long id);

}
