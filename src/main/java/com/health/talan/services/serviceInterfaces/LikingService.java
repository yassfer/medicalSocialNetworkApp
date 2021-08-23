package com.health.talan.services.serviceInterfaces;

import com.health.talan.entities.Liking;

import java.util.List;
import java.util.Optional;

public interface LikingService {

    public List<Liking> getAllLikes();

    public Optional<Liking> getLikingById(Long id);

    public Optional<Liking> findLikeByUserAndPublication(Long userId, Long publicationId);

    public String deleteLikeByUserAndPublication(Long userId, Long publicationId);

    public Liking saveLike(Long userId, Long pubId);

    public String deleteLiking(Long id);
    
    public Liking saveLikeChallenge(Long userId, Long pubId);

}
