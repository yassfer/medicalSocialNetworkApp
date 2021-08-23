package com.health.talan.services;

import com.health.talan.repositories.CommentRepo;
import com.health.talan.repositories.LikingRepo;
import com.health.talan.repositories.PieceJointRepo;
import com.health.talan.repositories.PublicationRepo;
import com.health.talan.entities.Publication;
import com.health.talan.entities.User;
import com.health.talan.services.serviceInterfaces.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublicationServiceImpl implements PublicationService {

    private final PublicationRepo publicationRepo;
    private final UserServiceImpl userServiceImpl;
    private final LikingRepo likingRepo;
    private final CommentRepo commentRepo;
    private final PieceJointRepo pieceJointRepo;

    @Autowired
    public PublicationServiceImpl(PublicationRepo publicationRepo, UserServiceImpl userServiceImpl,
                                  LikingRepo likingRepo, CommentRepo commentRepo, PieceJointRepo pieceJointRepo){

        this.publicationRepo = publicationRepo;
        this.userServiceImpl = userServiceImpl;
        this.likingRepo = likingRepo;
        this.commentRepo = commentRepo;
        this.pieceJointRepo = pieceJointRepo;
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
    public Publication publishPublication(Publication publication, Long userId){

        Optional<User> user = userServiceImpl.getUserById(userId);
        publication.setUser(user.get());

        Publication newPublication = publicationRepo.save(publication);

        return newPublication;
    }


    @Override
    public Publication updatePublication(Publication publication) {
        Publication newPublication = publicationRepo.save(publication);

        return newPublication;
    }


    @Override
    public String deletePublication(Long id){
        Optional<Publication> publication = publicationRepo.findById(id);
        if(publication.isPresent()){
            likingRepo.deleteByPublicationId(id);
            commentRepo.deleteByPublicationId(id);
            pieceJointRepo.deleteByPublicationId(id);
            publicationRepo.deletePublicationById(id);
            return "Publication Deleted";
        }
        else {
            return "Publication doesn't exit";
        }
    }

}
