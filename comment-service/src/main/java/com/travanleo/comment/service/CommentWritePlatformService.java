package com.travanleo.comment.service;


import com.travanleo.comment.api.JsonCommand;
import com.travanleo.comment.data.CommandProcessingResult;

public interface CommentWritePlatformService {

    CommandProcessingResult create(final JsonCommand command);

    CommandProcessingResult update(final String commentId, final JsonCommand command);

    CommandProcessingResult delete(final String commentId, final JsonCommand command);
}
