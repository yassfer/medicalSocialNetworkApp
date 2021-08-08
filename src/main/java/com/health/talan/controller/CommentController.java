package com.health.talan.controller;

import com.health.talan.entities.Comment;
import com.health.talan.service.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/comment")
public class CommentController {

    private final CommentServiceImpl commentServiceImpl;


    @Autowired
    public CommentController(CommentServiceImpl commentServiceImpl) {

        this.commentServiceImpl = commentServiceImpl;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Comment>> getAllComments() {

        List<Comment> comments = commentServiceImpl.getAllComments();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCommentById(@PathVariable("id") Long id) {
        Optional<Comment> comment = commentServiceImpl.getCommentById(id);
        if (comment.isPresent()) {
            return new ResponseEntity<>(comment, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("There's no comment with those userId and publicationId", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{userId}/publication/{publicationId}")
    public ResponseEntity<?> getCommentByUserIdAndPublicationId(@PathVariable("userId")Long user_Id, @PathVariable("publicationId")Long publication_Id) {

        Optional<Comment> comment = commentServiceImpl.findLikeByUserAndPublication(user_Id, publication_Id);
        if (comment.isPresent()){
            return new ResponseEntity<>(comment, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("There's no comment with those userId and publicationId", HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment) {

        Comment newComment = commentServiceImpl.saveComment(comment);
        return new ResponseEntity<>(newComment, HttpStatus.OK);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePublication(@PathVariable("id") Long id, @RequestBody Comment comment) {

        Optional<Comment> newComment = commentServiceImpl.getCommentById(id);
        if (newComment.isPresent()) {
            commentServiceImpl.saveComment(comment);
            return new ResponseEntity<>(newComment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Comment doesn't exist", HttpStatus.BAD_REQUEST);
        }

    }


    @DeleteMapping("/user/{userId}/publication/{publicationId}")
    public ResponseEntity<?> deleteCommentByUserAndPublication(@PathVariable("userId") Long userId, @PathVariable("publicationId") Long publicationId) {

        String message = commentServiceImpl.deleteLikeByUserAndPublication(userId, publicationId);
        if (message.equals("Comment Deleted")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable("id") Long id) {

        String message = commentServiceImpl.deleteComment(id);
        if (message.equals("Deleted")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
    }
}