package com.travanleo.comment.commands.data;

public class CommandWrapper {

    private final Long commandId;
    private final Long commentId;
    private final String actionName;
    private final String entityName;
    private final Long entityId;
    private final String href;
    private final String json;

    @SuppressWarnings("unused")
    private Long templateId;

    public CommandWrapper(final String actionName, final String entityName, final Long resourceId,
                          final String resourceGetUrl,
                          final Long commentId) {

        this.commandId = null;
        this.commentId = commentId;
        this.actionName = actionName;
        this.entityName = entityName;
        this.entityId = resourceId;
        this.href = resourceGetUrl;
        this.json = null;
    }

    public CommandWrapper(final Long commentId, final String actionName, final String entityName,
                          final Long resourceId,final String resourceGetUrl, String json) {

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

    public Long getEntityId() {
        return this.entityId;
    }

    public Long getCommentId() {
        return this.commentId;
    }

    public String getActionName() {
        return this.actionName;
    }

    public Long getCommandId() {
        return this.commandId;
    }
}
