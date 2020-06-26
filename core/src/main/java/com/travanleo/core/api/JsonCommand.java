package com.travanleo.core.api;

import com.google.gson.JsonElement;
import com.travanleo.core.serialization.FromJsonHelper;

/**
 * Immutable representation of a command.
 * 
 * Wraps the provided JSON with convenience functions for extracting parameter
 * values and checking for changes against an existing value.
 */
public final class JsonCommand {

    private final String jsonCommand;
    private final JsonElement parsedCommand;
    private final FromJsonHelper fromApiJsonHelper;
    private final Long commandId;
    private final Long resourceId;
    private final Long userId;
    private final String entityName;
    private final String url;

    public static JsonCommand from(final String jsonCommand, final JsonElement parsedCommand, final FromJsonHelper fromApiJsonHelper,
            final String entityName, final Long resourceId, final Long userId,
            final String url) {
        return new JsonCommand(null, jsonCommand, parsedCommand, fromApiJsonHelper, entityName, resourceId, userId, url);

    }

    public static JsonCommand fromExistingCommand(final Long commandId, final String jsonCommand, final JsonElement parsedCommand,
            final FromJsonHelper fromApiJsonHelper, final String entityName, final Long resourceId,
            final Long userId, final String url) {
        return new JsonCommand(commandId, jsonCommand, parsedCommand, fromApiJsonHelper, entityName, resourceId,
                userId, url);

    }

    public static JsonCommand fromExistingCommand(JsonCommand command, final JsonElement parsedCommand) {
        final String jsonCommand = command.fromApiJsonHelper.toJson(parsedCommand);
        return new JsonCommand(command.commandId, jsonCommand, parsedCommand, command.fromApiJsonHelper, command.entityName,
                command.resourceId, command.userId, command.url);
    }

    public JsonCommand(final Long commandId, final String jsonCommand, final JsonElement parsedCommand,
            final FromJsonHelper fromApiJsonHelper, final String entityName, final Long resourceId, final Long userId, final String url) {

        this.commandId = commandId;
        this.jsonCommand = jsonCommand;
        this.parsedCommand = parsedCommand;
        this.fromApiJsonHelper = fromApiJsonHelper;
        this.entityName = entityName;
        this.resourceId = resourceId;
        this.userId = userId;
        this.url = url;
    }
    
    public static JsonCommand fromJsonElement(final Long resourceId, final JsonElement parsedCommand) {
        return new JsonCommand(resourceId, parsedCommand);
    }
    
    public JsonCommand(final Long resourceId, final JsonElement parsedCommand) {
        this.parsedCommand = parsedCommand;
        this.resourceId = resourceId;
        this.commandId = null;
        this.jsonCommand = null;        
        this.fromApiJsonHelper = null;
        this.entityName = null;
        this.userId = null;
        this.url = null;
    }

    public String json() {
        return this.jsonCommand;
    }

    public JsonElement parsedJson() {
        return this.parsedCommand;
    }

    public JsonElement jsonElement(final String paramName) {
        if (this.parsedCommand.getAsJsonObject().has(paramName)) {
            return this.parsedCommand.getAsJsonObject().get(paramName);
        }
        return null;
    }
    
    public String jsonFragment(final String paramName) {
        String jsonFragment = null;
        if (this.parsedCommand.getAsJsonObject().has(paramName)) {
            final JsonElement fragment = this.parsedCommand.getAsJsonObject().get(paramName);
            jsonFragment = this.fromApiJsonHelper.toJson(fragment);
        }
        return jsonFragment;
    }

    public Long commandId() {
        return this.commandId;
    }

    public String entityName() {
        return this.entityName;
    }

    public Long entityId() {
        return this.resourceId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public String getUrl() {
        return this.url;
    }


    public boolean parameterExists(final String parameterName) {
        return this.fromApiJsonHelper.parameterExists(parameterName, this.parsedCommand);
    }

    public boolean hasParameter(final String parameterName) {
        return parameterExists(parameterName);
    }
}