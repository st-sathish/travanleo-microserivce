package com.travanleo.comment.commands.data;

public class CommandWrapper {

    private final String commandId;
    private final String commentId;
    private final String actionName;
    private final String entityName;
    private final String entityId;
    private final String href;
    private final String json;

    @SuppressWarnings("unused")
    private Long templateId;

    public CommandWrapper(final String actionName, final String entityName, final String resourceId,
                          final String resourceGetUrl,
                          final String commentId) {

        this.commandId = null;
        this.commentId = commentId;
        this.actionName = actionName;
        this.entityName = entityName;
        this.entityId = resourceId;
        this.href = resourceGetUrl;
        this.json = null;
    }

    public CommandWrapper(final String commentId, final String actionName, final String entityName,
                          final String resourceId,final String resourceGetUrl, String json) {

        this.commandId = null;
        this.commentId = commentId;
        this.actionName = actionName;
        this.entityName = entityName;
        this.entityId = resourceId;
        this.href = resourceGetUrl;
        this.json = json;
    }

    public String getHref() {
        return this.href;
    }

    public String getJson() {
        return this.json;
    }

    public String getEntityName() {
        return this.entityName;
    }

    public String getEntityId() {
        return this.entityId;
    }

    public String getCommentId() {
        return this.commentId;
    }

    public String getActionName() {
        return this.actionName;
    }

    public String getCommandId() {
        return this.commandId;
    }
}
