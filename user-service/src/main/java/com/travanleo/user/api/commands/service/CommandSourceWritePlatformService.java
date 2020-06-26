package com.travanleo.user.api.commands.service;

import com.travanleo.core.data.CommandProcessingResult;
import com.travanleo.user.api.commands.domain.CommandWrapper;

public interface CommandSourceWritePlatformService {

    CommandProcessingResult logCommandSource(final CommandWrapper wrapper);
}
