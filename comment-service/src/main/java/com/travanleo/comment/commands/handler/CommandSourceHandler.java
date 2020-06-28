package com.travanleo.comment.commands.handler;


import com.travanleo.comment.api.JsonCommand;
import com.travanleo.comment.data.CommandProcessingResult;

public interface CommandSourceHandler {

    CommandProcessingResult processCommand(JsonCommand command);

}
