package com.health.talan.services.serviceInterfaces;

import com.health.talan.entities.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    public List<Comment> getAllComments();

    public Optional<Comment> getCommentById(Long id);

    public Optional<Comment> findCommentByUserAndPublication(Long userId, Long publicationId);

    public Optional<List<Comment>> findCommentByUser(Long userId);

    public String deleteCommentByUserAndPublication(Long userId, Long publicationId);

    public Comment saveComment(Comment comment, Long userId, Long pubId);

    public String deleteComment(Long id);

    public Comment saveCommentPubCommunity(Comment comment, Long userId, Long pubId);

}
