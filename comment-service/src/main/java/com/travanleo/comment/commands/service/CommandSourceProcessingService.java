package com.travanleo.comment.commands.service;

import com.travanleo.comment.commands.data.CommandWrapper;
import com.travanleo.core.api.JsonCommand;
import com.travanleo.core.data.CommandProcessingResult;

public interface CommandSourceProcessingService {

    CommandProcessingResult processAndLogCommand(CommandWrapper wrapper, JsonCommand command);
}
