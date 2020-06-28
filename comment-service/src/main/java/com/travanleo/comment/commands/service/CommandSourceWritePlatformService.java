package com.travanleo.comment.commands.service;

import com.travanleo.comment.commands.data.CommandWrapper;
import com.travanleo.comment.data.CommandProcessingResult;

public interface CommandSourceWritePlatformService {

    CommandProcessingResult logCommandSource(final CommandWrapper commandWrapper);
}
