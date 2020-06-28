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
@CommandType(action = "UPDATE", entity = "COMMENT")
public class UpdateCommentCommandHandler implements CommandSourceHandler {

    private CommentWritePlatformService commandSourceWritePlatformService;

    @Autowired
    public UpdateCommentCommandHandler(final CommentWritePlatformService commandSourceWritePlatformService) {
        this.commandSourceWritePlatformService = commandSourceWritePlatformService;
    }

    @Override
    @Transactional
    public CommandProcessingResult processCommand(JsonCommand command) {
        return null;
    }
}