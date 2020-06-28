package com.travanleo.user.commands.service;


import com.travanleo.user.api.JsonCommand;
import com.travanleo.user.commands.domain.CommandWrapper;
import com.travanleo.user.data.CommandProcessingResult;

public interface CommandProcessingService {

    CommandProcessingResult processAndLogCommand(final CommandWrapper wrapper, final JsonCommand command);
}
