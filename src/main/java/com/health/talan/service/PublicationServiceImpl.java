package com.health.talan.service;

import com.health.talan.Repository.PublicationRepo;
import com.health.talan.entities.Publication;
import com.health.talan.entities.User;
import com.health.talan.service.serviceInterfaces.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublicationServiceImpl implements PublicationService {

    private final PublicationRepo publicationRepo;
    private final UserServiceImpl userServiceImpl;

    @Autowired
    public PublicationServiceImpl(PublicationRepo publicationRepo, UserServiceImpl userServiceImpl){

        this.publicationRepo = publicationRepo;
        this.userServiceImpl = userServiceImpl;
    }

    @Override
    public List<Publication> getAllPublications() {

        return publicationRepo.findAll();
    }


    @Override
    public Optional<Publication> getPublicationById(Long id){

        return Optional.ofNullable(publicationRepo.findById(id)).orElse(null);
    }


    @Override
    public Optional<List<Publication>> getPublicationByUserId(Long userId){

        return Optional.ofNullable(publicationRepo.findByUserId(userId)).orElse(null);
    }


    @Override
    public Publication publishPublication(Publication publication){
        Publication newPublication = publicationRepo.save(publication);
        Optional<User> user = userServiceImpl.getUserById(publication.getUser().getId());
        newPublication.setUser(user.get());

        return newPublication;
    }


    @Override
    public String deletePublication(Long id){
        Optional<Publication> publication = publicationRepo.findById(id);
        if(publication.isPresent()){
            publicationRepo.deleteById(id);
            return "Publication Deleted";
        }
        else {
            return "Publication doesn't exit";
        }
    }

}
