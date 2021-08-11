package com.health.talan.services;

import com.health.talan.repositories.LikingRepo;
import com.health.talan.entities.Liking;
import com.health.talan.entities.Publication;
import com.health.talan.entities.User;
import com.health.talan.services.serviceInterfaces.LikingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikingServiceImpl implements LikingService {

        private final LikingRepo likingRepo;
        private final UserServiceImpl userServiceImpl;
        private final PublicationServiceImpl publicationServiceImpl;

        @Autowired
        public LikingServiceImpl(LikingRepo likingRepo, UserServiceImpl userServiceImpl, PublicationServiceImpl publicationServiceImpl){

            this.likingRepo = likingRepo;
            this.userServiceImpl = userServiceImpl;
            this.publicationServiceImpl = publicationServiceImpl;
        }

        @Override
        public List<Liking> getAllLikes() {

            return likingRepo.findAll();
        }

        @Override
        public Optional<Liking> getLikingById(Long id){

            return Optional.ofNullable(likingRepo.findById(id)).orElse(null);
        }

        @Override
        public Optional<Liking> findLikeByUserAndPublication(Long userId, Long publicationId){

            return Optional.ofNullable(likingRepo.findByUserAndPublication(userId, publicationId)).orElse(null);
        }

        @Override
        public String deleteLikeByUserAndPublication(Long userId, Long publicationId){

            Optional<Liking> like = findLikeByUserAndPublication(userId, publicationId);
            if(like.isPresent()){
                likingRepo.deleteById(like.get().getId());
                return "like Deleted";
            }
            else {
                return "like doesn't exit";
            }
        }

        @Override
        public Liking saveLike(Long userId, Long pubId){
        	Liking like = new Liking();
            Optional<User> user = userServiceImpl.getUserById(userId);
            Optional<Publication> publication = publicationServiceImpl.getPublicationById(pubId);
            like.setPublication(publication.get());
            like.setUser(user.get());
            Liking newLike = likingRepo.save(like);
            return newLike;
        }

        @Override
        public String deleteLiking(Long id){
            Optional<Liking> like = likingRepo.findById(id);
            if(like.isPresent()){
                likingRepo.deleteById(id);
                return "like Deleted";
            }
            else {
                return "like doesn't exit";
            }
        }


}
