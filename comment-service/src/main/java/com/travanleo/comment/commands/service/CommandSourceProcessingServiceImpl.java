package com.travanleo.comment.commands.service;

import com.travanleo.comment.api.JsonCommand;
import com.travanleo.comment.commands.data.CommandWrapper;
import com.travanleo.comment.data.CommandProcessingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandSourceProcessingServiceImpl implements CommandSourceProcessingService {


    @Override
    public CommandProcessingResult processAndLogCommand(CommandWrapper wrapper, JsonCommand command) {
        return null;
    }
}

