package com.travanleo.user.commands.service;

import com.travanleo.core.api.JsonCommand;
import com.travanleo.core.commands.handler.CommandSourceHandler;
import com.travanleo.core.commands.provider.CommandHandlerProvider;
import com.travanleo.core.data.CommandProcessingResult;
import com.travanleo.user.commands.domain.CommandSource;
import com.travanleo.user.commands.domain.CommandWrapper;
import com.travanleo.user.commands.domain.JpaCommandSourceRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommandProcessingServiceImpl implements CommandProcessingService {

    private final CommandHandlerProvider commandHandlerProvider;
    private final JpaCommandSourceRepository commandSourceRepository;

    @Autowired
    public CommandProcessingServiceImpl(final CommandHandlerProvider commandHandlerProvider,
                                        final JpaCommandSourceRepository commandSourceRepository) {
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
            commandSource = CommandSource.fullEntryFrom(wrapper, command, 1L);
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
