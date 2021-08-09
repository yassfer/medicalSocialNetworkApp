package com.health.talan.service.serviceInterfaces;

import com.health.talan.entities.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    public List<Comment> getAllComments();

    public Optional<Comment> getCommentById(Long id);

    public Optional<Comment> findLikeByUserAndPublication(Long userId, Long publicationId);

    public String deleteLikeByUserAndPublication(Long userId, Long publicationId);

    public Comment saveComment(Comment comment, Long userId, Long pubId);

    public String deleteComment(Long id);
}
