package com.travanleo.comment.service;

import com.travanleo.comment.api.CommentApiConstants;
import com.travanleo.comment.api.JsonCommand;
import com.travanleo.comment.data.CommandProcessingResult;
import com.travanleo.comment.data.CommandProcessingResultBuilder;
import com.travanleo.comment.data.CommentDataValidator;
import com.travanleo.comment.domain.Comment;
import com.travanleo.comment.domain.CommentRepository;
import com.travanleo.comment.domain.CommentRepositoryWrapper;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CommentWritePlatformServiceImpl implements CommentWritePlatformService {

    private CommentDataValidator commentDataValidator;

    private CommentRepository commentRepository;

    private CommentRepositoryWrapper wrapper;

    @Autowired
    public CommentWritePlatformServiceImpl(final CommentDataValidator commentDataValidator,
                                           final CommentRepository commentRepository,
                                           final CommentRepositoryWrapper wrapper) {
        this.commentDataValidator = commentDataValidator;
        this.commentRepository = commentRepository;
        this.wrapper = wrapper;
    }

    @Override
    public CommandProcessingResult create(JsonCommand command) {
        try {
            CommandProcessingResult result = null;
            this.commentDataValidator.validateCreateComment(command.json());
            String title = command.stringValueOfParameterNamed(CommentApiConstants.titleParamName);
            String description = command.stringValueOfParameterNamed(CommentApiConstants.descriptionParamName);
            final String userName = (String) SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getPrincipal();
            // save Comment
            Comment comment = new Comment(title, description, userName, DateTime.now());
            commentRepository.save(comment);

            result = new CommandProcessingResultBuilder()
                    .withEntityId(comment.getId())
                    .build();
            return result;
        } catch (Exception e) {
            return CommandProcessingResult.empty();
        }
    }

    @Override
    public CommandProcessingResult update(final String commentId, final JsonCommand command) {
        try {
            final Comment comment = wrapper.findByIdOrThrowException(commentId);
            // validate
            commentDataValidator.validateUpdateComment(command.json());

            final Map<String, Object> changes = comment.update(command);
            if(changes.containsKey("title")) {
                String title = command.stringValueOfParameterNamed("title");
                comment.updateTitle(title);
            }
            if(changes.containsKey("description")) {
                String description = command.stringValueOfParameterNamed("description");
                comment.updateDescription(description);
            }
            if (!changes.isEmpty()) {
                comment.setUpdatedAt(DateTime.now());
                this.commentRepository.save(comment);
            }
            return new CommandProcessingResultBuilder()
                    .withCommandId(command.commandId())
                    .withCommentId(commentId)
                    .withEntityId(commentId)
                    .with(changes)
                    .build();
        } catch (Exception e) {
            return CommandProcessingResult.empty();
        }
    }

    @Override
    public CommandProcessingResult delete(final String commentId, final JsonCommand command) {
        final Comment comment = wrapper.findByIdOrThrowException(commentId);
        this.commentRepository.delete(comment);
        return new CommandProcessingResultBuilder()
                .withCommentId(commentId)
                .withEntityId(commentId)
                .build();
    }
}
