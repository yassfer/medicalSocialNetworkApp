package com.health.talan.services;

import com.health.talan.repositories.CommentRepo;
import com.health.talan.entities.Comment;
import com.health.talan.entities.Publication;
import com.health.talan.entities.User;
import com.health.talan.services.serviceInterfaces.CommentService;
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
    public Optional<Comment> findCommentByUserAndPublication(Long userId, Long publicationId){

        return Optional.ofNullable(commentRepo.findByUserAndPublication(userId, publicationId)).orElse(null);
    }

    @Override
    public String deleteCommentByUserAndPublication(Long userId, Long publicationId){

        Optional<Comment> comment = findCommentByUserAndPublication(userId, publicationId);
        if(comment.isPresent()){
            commentRepo.deleteById(comment.get().getId());
            return "comment Deleted";
        }
        else {
            return "comment doesn't exit";
        }
    }


    @Override
    public Optional<List<Comment>> findCommentByUser(Long userId){
        Optional<List<Comment>> comments = commentRepo.findByUser(userId);
        return comments;
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
            commentRepo.deleteCommenById(id);
            return "Comment Deleted";
        }
        else {
            return "Comment doesn't exit";
        }
    }

}
