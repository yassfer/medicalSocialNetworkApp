package com.health.talan.services.serviceInterfaces;

import com.health.talan.entities.PublicationCommunity;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface PublicationCommunityService {

    public List<PublicationCommunity> getAllPublications();

    public Optional<PublicationCommunity> getPublicationById(Long id);

    public Optional<List<PublicationCommunity>> getPublicationByUserId(Long userId);

    public PublicationCommunity addPublicationCommunity(PublicationCommunity publicationCommunity, Long communityId, Long userId) throws IOException;

    public PublicationCommunity updatePublication(PublicationCommunity publication);

    public Optional<List<PublicationCommunity>> getPublicationCommunityByCommunityId(Long communityId);

    public String deletePublication(Long id);

    public List<PublicationCommunity> getAllByDate();
}
