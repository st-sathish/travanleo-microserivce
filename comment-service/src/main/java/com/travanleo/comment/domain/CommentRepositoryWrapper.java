package com.travanleo.comment.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CommentRepositoryWrapper {

    private CommentRepository commentRepository;

    @Autowired
    public CommentRepositoryWrapper(final CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment findByIdOrThrowException(final String commentId) {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        if(commentOptional.isEmpty()) {
            throw new RuntimeException(("Document not found"));
        }
        return commentOptional.get();
    }
}
