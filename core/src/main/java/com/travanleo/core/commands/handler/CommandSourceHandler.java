package com.travanleo.core.commands.handler;

import com.travanleo.core.api.JsonCommand;
import com.travanleo.core.data.CommandProcessingResult;

public interface CommandSourceHandler {

    CommandProcessingResult processCommand(JsonCommand command);

}
