package com.health.talan.service;

import com.health.talan.Repository.CommentRepo;
import com.health.talan.entities.Comment;
import com.health.talan.entities.Publication;
import com.health.talan.entities.User;
import com.health.talan.service.serviceInterfaces.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepo commentRepo;
    private final UserServiceImpl userServiceImpl;
    private final PublicationServiceImpl publicationServiceImpl;

    @Autowired
    public CommentServiceImpl(CommentRepo commentRepo, UserServiceImpl userServiceImpl, PublicationServiceImpl publicationServiceImpl){

        this.commentRepo = commentRepo;
        this.userServiceImpl = userServiceImpl;
        this.publicationServiceImpl = publicationServiceImpl;
    }

    @Override
    public List<Comment> getAllComments() {

        return commentRepo.findAll();
    }

    @Override
    public Optional<Comment> getCommentById(Long id){

        return Optional.ofNullable(commentRepo.findById(id)).orElse(null);
    }

    @Override
    public Optional<Comment> findLikeByUserAndPublication(Long userId, Long publicationId){

        return Optional.ofNullable(commentRepo.findByUserAndPublication(userId, publicationId)).orElse(null);
    }

    @Override
    public String deleteLikeByUserAndPublication(Long userId, Long publicationId){

        Optional<Comment> comment = findLikeByUserAndPublication(userId, publicationId);
        if(comment.isPresent()){
            commentRepo.deleteById(comment.get().getId());
            return "comment Deleted";
        }
        else {
            return "comment doesn't exit";
        }
    }

    @Override
    public Comment saveComment(Comment comment, Long userId, Long pubId){
        Optional<User> user = userServiceImpl.getUserById(userId);
        comment.setUser(user.get());

        Optional<Publication> publication = publicationServiceImpl.getPublicationById(pubId);
        comment.setPublication(publication.get());

        Comment newComment = commentRepo.save(comment);
        return newComment;
    }


    @Override
    public String deleteComment(Long id){
        Optional<Comment> comment = commentRepo.findById(id);
        if(comment.isPresent()){
            commentRepo.deleteById(id);
            return "Comment Deleted";
        }
        else {
            return "Comment doesn't exit";
        }
    }
}
