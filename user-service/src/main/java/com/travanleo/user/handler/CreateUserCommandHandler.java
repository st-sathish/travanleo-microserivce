package com.travanleo.user.handler;

import com.travanleo.user.service.UserWritePlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateUserCommandHandler {

    private final UserWritePlatformService userWritePlatformService;

    @Autowired
    public CreateUserCommandHandler(final UserWritePlatformService userWritePlatformService) {
        this.userWritePlatformService = userWritePlatformService;
    }
}
