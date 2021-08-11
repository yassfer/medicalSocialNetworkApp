package com.health.talan.services.serviceInterfaces;

import com.health.talan.entities.Publication;

import java.util.List;
import java.util.Optional;

public interface PublicationService {

    public List<Publication> getAllPublications();

    public Optional<Publication> getPublicationById(Long id);

    public Optional<List<Publication>> getPublicationByUserId(Long userId);

    public Publication publishPublication(Publication publication, Long userId);

    public Publication updatePublication(Publication publication);

    public String deletePublication(Long id);
}
