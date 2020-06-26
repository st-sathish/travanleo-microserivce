package com.travanleo.user.api.commands.service;

import com.google.gson.JsonElement;
import com.travanleo.core.api.JsonCommand;
import com.travanleo.core.data.CommandProcessingResult;
import com.travanleo.core.serialization.FromJsonHelper;
import com.travanleo.user.api.commands.domain.CommandWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandSourceWritePlatformServiceImpl implements CommandSourceWritePlatformService {

    private FromJsonHelper fromApiJsonHelper;

    @Autowired
    public CommandSourceWritePlatformServiceImpl(final FromJsonHelper fromApiJsonHelper) {
        this.fromApiJsonHelper = fromApiJsonHelper;
    }

    @Override
    public CommandProcessingResult logCommandSource(CommandWrapper wrapper) {
        final String json = wrapper.getJson();
        CommandProcessingResult result = null;
        JsonCommand command = null;
        final JsonElement parsedCommand = this.fromApiJsonHelper.parse(json);
        return null;
    }
}
