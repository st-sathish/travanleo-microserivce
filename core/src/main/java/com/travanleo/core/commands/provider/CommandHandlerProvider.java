package com.travanleo.core.commands.provider;

import com.travanleo.core.commands.annotation.CommandType;
import com.travanleo.core.commands.handler.CommandSourceHandler;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@Scope("singleton")
public class CommandHandlerProvider implements ApplicationContextAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandHandlerProvider.class);

    private ApplicationContext applicationContext;
    private HashMap<String, String> registeredHandlers;

    public CommandHandlerProvider() {
        super();
    }

    public CommandSourceHandler getHandler(final String entity, final String action) {
        final String key =  entity + "|" + action;
        if (!this.registeredHandlers.containsKey(key)) {
            throw new RuntimeException(key);
        }
        return (CommandSourceHandler)this.applicationContext.getBean(this.registeredHandlers.get(key));
    }

    private void initializeHandlerRegistry() {
        if (this.registeredHandlers == null) {
            this.registeredHandlers = new HashMap<>();

            final String[] commandHandlerBeans = this.applicationContext.getBeanNamesForAnnotation(CommandType.class);
            if (ArrayUtils.isNotEmpty(commandHandlerBeans)) {
                for (final String commandHandlerName : commandHandlerBeans) {
                    LOGGER.info("Register command handler '" + commandHandlerName + "' ...");
                    final CommandType commandType = this.applicationContext.findAnnotationOnBean(commandHandlerName, CommandType.class);
                    try {
                        this.registeredHandlers.put(commandType.entity() + "|" + commandType.action(), commandHandlerName);
                    } catch (final Throwable th) {
                        LOGGER.error("Unable to register command handler '" + commandHandlerName + "'!", th);
                    }
                }
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        initializeHandlerRegistry();
    }
}
