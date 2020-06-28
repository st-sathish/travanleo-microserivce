package com.travanleo.comment.commands.service;

import com.travanleo.comment.commands.data.CommandWrapper;

public class CommandWrapperBuilder {

    private Long commentId;
    private String actionName;
    private String entityName;
    private Long entityId;
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
        this.href = "/comments/template";
        return this;
    }

    public CommandWrapperBuilder withJson(final String withJson) {
        this.json = withJson;
        return this;
    }
}