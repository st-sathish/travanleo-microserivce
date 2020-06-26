package com.travanleo.user.api.commands.domain;

public class CommandWrapper {

    private final Long commandId;
    private final Long userId;
    private final String actionName;
    private final String entityName;
    private final Long entityId;
    private final String href;
    private final String json;

    @SuppressWarnings("unused")
    private Long templateId;

    public CommandWrapper(final String actionName, final String entityName, final Long resourceId,
                          final String resourceGetUrl,
                          final Long userId) {

        this.commandId = null;
        this.userId = userId;
        this.actionName = actionName;
        this.entityName = entityName;
        this.entityId = resourceId;
        this.href = resourceGetUrl;
        this.json = null;
    }

    public CommandWrapper(final Long userId, final String actionName, final String entityName,
                          final Long resourceId,final String resourceGetUrl, String json) {

        this.commandId = null;
        this.userId = userId;
        this.actionName = actionName;
        this.entityName = entityName;
        this.entityId = resourceId;
        this.href = resourceGetUrl;
        this.json = json;
    }
}