package com.travanleo.comment.service;

import com.travanleo.comment.api.CommentApiConstants;
import com.travanleo.comment.api.JsonCommand;
import com.travanleo.comment.data.CommandProcessingResult;
import com.travanleo.comment.data.CommandProcessingResultBuilder;
import com.travanleo.comment.data.CommentDataValidator;
import com.travanleo.comment.domain.Comment;
import com.travanleo.comment.domain.CommentRepository;
import com.travanleo.comment.domain.CommentRepositoryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

            // save Comment
            Comment comment = new Comment(title, description);
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
    public CommandProcessingResult update(String commentId, JsonCommand command) {
        Comment comment = wrapper.findByIdOrThrowException(commentId);
        return null;
    }

    @Override
    public CommandProcessingResult delete(String commentId, JsonCommand command) {
        return null;
    }
}
