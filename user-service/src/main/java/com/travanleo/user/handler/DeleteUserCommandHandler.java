package com.travanleo.user.handler;

import com.travanleo.core.commands.annotation.CommandType;
import com.travanleo.user.api.JsonCommand;
import com.travanleo.user.commands.handler.CommandSourceHandler;
import com.travanleo.user.data.CommandProcessingResult;
import com.travanleo.user.service.UserWritePlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@CommandType(entity ="USER", action = "DELETE")
public class DeleteUserCommandHandler implements CommandSourceHandler {

    private final UserWritePlatformService userWritePlatformService;

    @Autowired
    public DeleteUserCommandHandler(final UserWritePlatformService userWritePlatformService) {
        this.userWritePlatformService = userWritePlatformService;
    }

    @Override
    public CommandProcessingResult processCommand(JsonCommand command) {
        return userWritePlatformService.deleteUser(command.getUserId(), command);
    }
}
