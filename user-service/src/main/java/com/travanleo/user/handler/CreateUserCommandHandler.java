package com.travanleo.user.handler;

import com.travanleo.core.commands.annotation.CommandType;
import com.travanleo.user.api.JsonCommand;
import com.travanleo.user.commands.handler.CommandSourceHandler;
import com.travanleo.user.data.CommandProcessingResult;
import com.travanleo.user.service.UserWritePlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@CommandType(entity ="USER", action = "CREATE")
public class CreateUserCommandHandler implements CommandSourceHandler {

    private final UserWritePlatformService userWritePlatformService;

    @Autowired
    public CreateUserCommandHandler(final UserWritePlatformService userWritePlatformService) {
        this.userWritePlatformService = userWritePlatformService;
    }

    @Override
    @Transactional
    public CommandProcessingResult processCommand(JsonCommand command) {
        return this.userWritePlatformService.createUser(command);
    }
}
