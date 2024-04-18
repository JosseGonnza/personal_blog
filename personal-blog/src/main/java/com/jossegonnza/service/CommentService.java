package com.jossegonnza.service;

import com.jossegonnza.entity.CommentEntity;

import java.util.Optional;

public interface CommentService {

    Optional<CommentEntity> getCommentById(Long id);
    void createComment(CommentEntity comment);
    void udateComment(Long id, CommentEntity comment);
    void deleteComment(Long id);
}
