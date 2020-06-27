package com.travanleo.comment.commands.service;

import com.google.gson.JsonElement;
import com.travanleo.comment.commands.data.CommandWrapper;
import com.travanleo.core.api.JsonCommand;
import com.travanleo.core.data.CommandProcessingResult;
import com.travanleo.core.serialization.FromJsonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandSourceWritePlatformServiceImpl implements CommandSourceWritePlatformService {

    private final CommandSourceProcessingService commandSourceProcessingService;

    private final FromJsonHelper fromApiJsonHelper;

    @Autowired
    public CommandSourceWritePlatformServiceImpl(final CommandSourceProcessingService commandSourceProcessingService,
                                                 final FromJsonHelper fromApiJsonHelper) {
        this.commandSourceProcessingService = commandSourceProcessingService;
        this.fromApiJsonHelper = fromApiJsonHelper;
    }

    @Override
    public CommandProcessingResult logCommandSource(CommandWrapper commandWrapper) {
        final String json = commandWrapper.getJson();
        CommandProcessingResult result = null;
        JsonCommand command = null;
        final JsonElement parsedCommand = this.fromApiJsonHelper.parse(json);
        command = JsonCommand.from(json, parsedCommand, this.fromApiJsonHelper,
                commandWrapper.getEntityName(), commandWrapper.getEntityId(),
                commandWrapper.getCommentId(), commandWrapper.getHref());
        return this.commandSourceProcessingService.processAndLogCommand(commandWrapper, command);
    }
}
