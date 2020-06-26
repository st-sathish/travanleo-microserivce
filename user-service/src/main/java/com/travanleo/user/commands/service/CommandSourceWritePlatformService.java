package com.travanleo.user.commands.service;

import com.travanleo.core.data.CommandProcessingResult;
import com.travanleo.user.commands.domain.CommandWrapper;

public interface CommandSourceWritePlatformService {

    CommandProcessingResult logCommandSource(final CommandWrapper wrapper);
}
