package com.travanleo.core.commands.handler;

import com.travanleo.core.api.JsonCommand;
import com.travanleo.core.data.CommandProcessingResult;

public interface CommandSourceHander {

    CommandProcessingResult processCommand(JsonCommand command);

}
