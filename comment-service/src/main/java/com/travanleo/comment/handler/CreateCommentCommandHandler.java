package com.travanleo.comment.handler;

import com.travanleo.comment.api.JsonCommand;
import com.travanleo.comment.commands.handler.CommandSourceHandler;
import com.travanleo.comment.commands.service.CommandSourceWritePlatformService;
import com.travanleo.comment.data.CommandProcessingResult;
import com.travanleo.comment.service.CommentWritePlatformService;
import com.travanleo.core.commands.annotation.CommandType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@CommandType(action = "CREATE", entity = "COMMENT")
public class CreateCommentCommandHandler implements CommandSourceHandler {

    private CommentWritePlatformService commentWritePlatformService;

    @Autowired
    public CreateCommentCommandHandler(final CommentWritePlatformService commentWritePlatformService) {
        this.commentWritePlatformService = commentWritePlatformService;
    }

    @Override
    @Transactional
    public CommandProcessingResult processCommand(JsonCommand command) {
        return null;
    }
}
