package com.travanleo.comment.commands.service;

import com.travanleo.comment.api.JsonCommand;
import com.travanleo.comment.commands.data.CommandWrapper;
import com.travanleo.comment.data.CommandProcessingResult;

public interface CommandSourceProcessingService {

    CommandProcessingResult processAndLogCommand(CommandWrapper wrapper, JsonCommand command);
}
