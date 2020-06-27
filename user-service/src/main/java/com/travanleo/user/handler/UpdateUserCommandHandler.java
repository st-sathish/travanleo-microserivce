package com.travanleo.user.handler;

import com.travanleo.core.api.JsonCommand;
import com.travanleo.core.commands.annotation.CommandType;
import com.travanleo.core.commands.handler.CommandSourceHandler;
import com.travanleo.core.data.CommandProcessingResult;
import com.travanleo.user.service.UserWritePlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@CommandType(entity ="USER", action = "UPDATE")
public class UpdateUserCommandHandler implements CommandSourceHandler {

    private final UserWritePlatformService userWritePlatformService;

    @Autowired
    public UpdateUserCommandHandler(final UserWritePlatformService userWritePlatformService) {
        this.userWritePlatformService = userWritePlatformService;
    }

    @Transactional
    @Override
    public CommandProcessingResult processCommand(final JsonCommand command) {
        return userWritePlatformService.updateUser(command.getUserId(), command);
    }
}
