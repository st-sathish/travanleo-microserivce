package com.travanleo.user.handler;

import com.travanleo.core.commands.annotation.CommandType;
import com.travanleo.user.service.UserWritePlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@CommandType(entity ="USER", action = "DELETE")
public class DeleteUserCommandHandler {

    private final UserWritePlatformService userWritePlatformService;

    @Autowired
    public DeleteUserCommandHandler(final UserWritePlatformService userWritePlatformService) {
        this.userWritePlatformService = userWritePlatformService;
    }
}
