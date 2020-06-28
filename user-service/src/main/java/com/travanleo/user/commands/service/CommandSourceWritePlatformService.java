package com.travanleo.user.commands.service;

import com.travanleo.user.commands.domain.CommandWrapper;
import com.travanleo.user.data.CommandProcessingResult;

public interface CommandSourceWritePlatformService {

    CommandProcessingResult logCommandSource(final CommandWrapper wrapper);
}
