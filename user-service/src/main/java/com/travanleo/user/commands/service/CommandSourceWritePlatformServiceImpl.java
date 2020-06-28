package com.travanleo.user.commands.service;

import com.google.gson.JsonElement;
import com.travanleo.core.serialization.FromJsonHelper;
import com.travanleo.user.api.JsonCommand;
import com.travanleo.user.commands.domain.CommandWrapper;
import com.travanleo.user.data.CommandProcessingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Use this service in all *ApiResource java class to perform write operation.
 * This will log all the commands performed by the user
 */
@Service
public class CommandSourceWritePlatformServiceImpl implements CommandSourceWritePlatformService {

    private FromJsonHelper fromApiJsonHelper;

    private CommandProcessingService commandProcessingService;

    @Autowired
    public CommandSourceWritePlatformServiceImpl(final FromJsonHelper fromApiJsonHelper,
                                                 final CommandProcessingService commandProcessingService) {
        this.fromApiJsonHelper = fromApiJsonHelper;
        this.commandProcessingService = commandProcessingService;
    }

    @Override
    public CommandProcessingResult logCommandSource(final CommandWrapper wrapper) {
        final String json = wrapper.getJson();
        CommandProcessingResult result = null;
        JsonCommand command = null;
        final JsonElement parsedCommand = this.fromApiJsonHelper.parse(json);
        command = JsonCommand.from(json, parsedCommand, this.fromApiJsonHelper,
                wrapper.getEntityName(), wrapper.getEntityId(), wrapper.getUserId(), wrapper.getHref());
        try {
            result = commandProcessingService.processAndLogCommand(wrapper, command);
        } catch (Exception e) {
            // handle exception
        }
        return result;
    }
}
