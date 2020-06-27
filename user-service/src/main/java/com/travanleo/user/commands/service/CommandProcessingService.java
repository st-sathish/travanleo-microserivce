package com.travanleo.user.commands.service;

import com.travanleo.core.api.JsonCommand;
import com.travanleo.core.data.CommandProcessingResult;
import com.travanleo.user.commands.domain.CommandWrapper;

public interface CommandProcessingService {

    CommandProcessingResult processAndLogCommand(final CommandWrapper wrapper, final JsonCommand command);
}
