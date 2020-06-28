package com.travanleo.user.commands.handler;


import com.travanleo.user.api.JsonCommand;
import com.travanleo.user.data.CommandProcessingResult;

public interface CommandSourceHandler {

    CommandProcessingResult processCommand(JsonCommand command);

}
