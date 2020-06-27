package com.travanleo.comment.handler;

import com.travanleo.core.api.JsonCommand;
import com.travanleo.core.commands.annotation.CommandType;
import com.travanleo.core.commands.handler.CommandSourceHandler;
import com.travanleo.core.data.CommandProcessingResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@CommandType(entity = "COMMENT", action = "DELETE")
public class DeleteCommentCommandHandler implements CommandSourceHandler {

    @Override
    @Transactional
    public CommandProcessingResult processCommand(JsonCommand command) {
        return null;
    }
}
