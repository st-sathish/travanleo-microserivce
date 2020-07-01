package com.travanleo.comment.commands.service;

import com.travanleo.comment.api.JsonCommand;
import com.travanleo.comment.commands.data.CommandWrapper;
import com.travanleo.comment.commands.domain.CommandSource;
import com.travanleo.comment.commands.domain.CommandSourceRepository;
import com.travanleo.comment.commands.handler.CommandSourceHandler;
import com.travanleo.comment.commands.provider.CommandHandlerProvider;
import com.travanleo.comment.data.CommandProcessingResult;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommandSourceProcessingServiceImpl implements CommandSourceProcessingService {

    private final CommandHandlerProvider commandHandlerProvider;

    private final CommandSourceRepository commandSourceRepository;

    @Autowired
    public CommandSourceProcessingServiceImpl(final CommandHandlerProvider commandHandlerProvider,
                                              final CommandSourceRepository commandSourceRepository) {
        this.commandHandlerProvider = commandHandlerProvider;
        this.commandSourceRepository = commandSourceRepository;
    }

    @Override
    public CommandProcessingResult processAndLogCommand(CommandWrapper wrapper, JsonCommand command) {
        final CommandSourceHandler handler = findCommandHandler(wrapper);
        final CommandProcessingResult result = handler.processCommand(command);
        CommandSource commandSource;
        if (command.commandId() != null) {
            Optional<CommandSource> commandSourceOptional = this.commandSourceRepository.findById(command.commandId());
            commandSource = commandSourceOptional.get();
            commandSource.markAsChecked(DateTime.now());
        } else {
            commandSource = CommandSource.fullEntryFrom(wrapper, command, command.getUserId());
        }
        commandSource.updateResourceId(result.getResourceId());
        if (commandSource.hasJson()) {
            this.commandSourceRepository.save(commandSource);
        }
        return result;
    }

    private CommandSourceHandler findCommandHandler(final CommandWrapper wrapper) {
        return this.commandHandlerProvider.getHandler(wrapper.getEntityName(), wrapper.getActionName());
    }
}

