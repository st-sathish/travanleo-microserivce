package com.travanleo.comment.service;

import com.travanleo.comment.data.CommentData;
import com.travanleo.comment.domain.Comment;
import com.travanleo.comment.domain.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentReadPlatformServiceImpl implements CommentReadPlatformService {

    private final CommentRepository commentRepository;

    private final CommentRowMapper rowMapper = new CommentRowMapper();

    @Autowired
    public CommentReadPlatformServiceImpl(final CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<CommentData> retrieveMyCommentList() {
        final String user = (String) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        return rowMapper.mapRow(commentRepository.findByCreatedBy(user));
    }

    private static class CommentRowMapper {

        public List<CommentData> mapRow(final List<Comment> comments) {
            final List<CommentData> commentDataList = new ArrayList<>();
            for(final Comment comment: comments) {
                commentDataList.add(mapRow(comment));
            }
            return commentDataList;
        }

        public CommentData mapRow(final Comment comment) {
            DateFormat simple = new SimpleDateFormat("dd MMM yyyy HH:mm");
            String createdAt = simple.format(comment.getCreatedAt().getMillis());
            String updatedAt = simple.format(comment.getUpdatedAt().getMillis());
            return new CommentData(comment.getTitle(), comment.getDescription(),
                    createdAt, updatedAt);
        }
    }
}
