package com.travanleo.user.api.commands.service;

import com.google.gson.JsonElement;
import com.travanleo.core.api.JsonCommand;
import com.travanleo.core.data.CommandProcessingResult;
import com.travanleo.core.serialization.FromJsonHelper;
import com.travanleo.user.api.commands.domain.CommandWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Use this service in all *ApiResource java class to perform write operation.
 * This will log all the commands performed by the user
 */
@Service
public class CommandSourceWritePlatformServiceImpl implements CommandSourceWritePlatformService {

    private FromJsonHelper fromApiJsonHelper;

    @Autowired
    public CommandSourceWritePlatformServiceImpl(final FromJsonHelper fromApiJsonHelper) {
        this.fromApiJsonHelper = fromApiJsonHelper;
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

        } catch (Exception e) {
            // handle exception
        }

        //create empty response
        result = CommandProcessingResult.empty();
        return result;
    }
}
