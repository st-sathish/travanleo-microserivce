package com.travanleo.user.api.commands.service;

import com.travanleo.core.data.CommandProcessingResult;
import com.travanleo.user.api.commands.domain.CommandWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandSourceWritePlatformServiceImpl implements CommandSourceWritePlatformService {

    @Autowired
    public CommandSourceWritePlatformServiceImpl() {

    }

    @Override
    public CommandProcessingResult logCommandSource(CommandWrapper wrapper) {
        return null;
    }
}
