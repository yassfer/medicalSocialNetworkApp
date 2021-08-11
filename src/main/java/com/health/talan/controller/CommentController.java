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


@CrossOrigin(origins = "http://localhost:4200")
@Controller
@RequestMapping("api/comment")
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
            return new ResponseEntity<>("There's no comment with that id", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{userId}/publication/{publicationId}")
    public ResponseEntity<?> getCommentByUserIdAndPublicationId(@PathVariable("userId")Long user_Id, @PathVariable("publicationId")Long publication_Id) {

        Optional<Comment> comment = commentServiceImpl.findCommentByUserAndPublication(user_Id, publication_Id);
        if (comment.isPresent()){
            return new ResponseEntity<>(comment, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("There's no comment with those userId and publicationId", HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getCommentsByUserId(@PathVariable("userId")Long userId) {

        Optional<List<Comment>> comments = commentServiceImpl.findCommentByUser(userId);
        if (comments.isPresent()){
            return new ResponseEntity<>(comments, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("There's no comments with that userId", HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("user/{userId}/pub/{pubId}")
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment, @PathVariable("userId") Long userId, @PathVariable("pubId") Long pubId) {

        Comment newComment = commentServiceImpl.saveComment(comment, userId, pubId);
        return new ResponseEntity<>(newComment, HttpStatus.OK);
    }


    @PatchMapping("/{id}/user/{userId}/pub/{pubId}")
    public ResponseEntity<?> updatePublication(@PathVariable("id") Long id, @PathVariable("userId") Long userId,
                                               @PathVariable("pubId") Long pubId,@RequestBody Comment comment) {


        Optional<Comment> newComment = commentServiceImpl.getCommentById(id);
        if (newComment.isPresent()) {
            commentServiceImpl.saveComment(comment, userId, pubId);
            return new ResponseEntity<>(newComment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Comment doesn't exist", HttpStatus.BAD_REQUEST);
        }

    }


    @DeleteMapping("/user/{userId}/publication/{publicationId}")
    public ResponseEntity<?> deleteCommentByUserAndPublication(@PathVariable("userId") Long userId, @PathVariable("publicationId") Long publicationId) {

        String message = commentServiceImpl.deleteCommentByUserAndPublication(userId, publicationId);
        if (message.equals("Comment Deleted")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable("id") Long id) {

        String message = commentServiceImpl.deleteComment(id);
        if (message.equals("Comment Deleted")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
    }
}
