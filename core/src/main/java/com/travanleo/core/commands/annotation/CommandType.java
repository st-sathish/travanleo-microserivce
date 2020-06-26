package com.travanleo.core.commands.annotation;

import java.lang.annotation.*;

/**
 * annotation to handle commands performed by the application
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface CommandType {

    /**
     * Returns the name of the entity for this {@link CommandType}.
     */
    String entity();

    /**
     * Return the name of the action for this {@link CommandType}.
     */
    String action();
}
