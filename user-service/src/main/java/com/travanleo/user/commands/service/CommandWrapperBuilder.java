package com.travanleo.user.commands.service;

import com.travanleo.user.commands.domain.CommandWrapper;

public class CommandWrapperBuilder {

    private Long userId;
    private String actionName;
    private String entityName;
    private Long entityId;
    private String href;
    private String json = "{}";
   

    public CommandWrapper build() {
        return new CommandWrapper(this.userId, this.actionName,
                this.entityName, this.entityId, this.href, this.json);
    }

    public CommandWrapperBuilder withJson(final String withJson) {
        this.json = withJson;
        return this;
    }

    public CommandWrapperBuilder withNoJsonBody() {
        this.json = null;
        return this;
    }

    public CommandWrapperBuilder createUser() {
        this.actionName = "CREATE";
        this.entityName = "USER";
        this.entityId = null;
        this.href = "/users/template";
        return this;
    }

    public CommandWrapperBuilder updateUser(final Long userId) {
        this.actionName = "UPDATE";
        this.entityName = "USER";
        this.entityId = userId;
        this.userId = userId;
        this.href = "/users/" + userId;
        return this;
    }

    public CommandWrapperBuilder deleteUser(final Long userId) {
        this.actionName = "DELETE";
        this.entityName = "USER";
        this.entityId = userId;
        this.userId = userId;
        this.href = "/users/" + userId;
        this.json = "{}";
        return this;
    }
}
