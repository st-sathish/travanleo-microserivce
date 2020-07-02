package com.travanleo.comment.commands.service;

import com.travanleo.comment.commands.data.CommandWrapper;

public class CommandWrapperBuilder {

    private String commentId;
    private String actionName;
    private String entityName;
    private String entityId;
    private String href;
    private String json = "{}";

    public CommandWrapper build() {
        return new CommandWrapper(this.commentId, this.actionName, this.entityName,
                this.entityId, this.href, this.json);
    }

    public CommandWrapperBuilder createComment() {
        this.actionName = "CREATE";
        this.entityName = "COMMENT";
        this.entityId = null;
        this.href = "/comments";
        return this;
    }

    public CommandWrapperBuilder updateComment(final String commentId) {
        this.actionName = "UPDATE";
        this.entityName = "COMMENT";
        this.entityId = commentId;
        this.commentId = commentId;
        this.href = "/comments/" + commentId;
        return this;
    }

    public CommandWrapperBuilder deleteComment(final String commentId) {
        this.actionName = "DELETE";
        this.entityName = "COMMENT";
        this.entityId = commentId;
        this.commentId = commentId;
        this.href = "/comments/" + commentId;
        this.json = "{}";
        return this;
    }

    public CommandWrapperBuilder withJson(final String withJson) {
        this.json = withJson;
        return this;
    }
}
