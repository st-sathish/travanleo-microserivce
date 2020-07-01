package com.travanleo.comment.commands.domain;

import com.travanleo.comment.api.JsonCommand;
import com.travanleo.comment.commands.data.CommandWrapper;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "command_source")
public class CommandSource {

    @Id
    private String id;

    @Field(name = "action_name")
    private String actionName;

    @Field(name = "entity_name")
    private String entityName;

    @Field(name = "user_id")
    private Long userId;

    @Field(name = "api_get_url")
    private String resourceGetUrl;

    @Field(name = "resource_id")
    private String resourceId;

    @Field(name = "command_as_json")
    private String commandAsJson;

    @Field(name = "created_on_date")
    private Date createdOnDate;

    @Field(name = "processing_result_enum")
    private Integer processingResult;

    @Field(name = "checked_on_date")
    private Date checkedOnDate;

    public static CommandSource fullEntryFrom(final CommandWrapper wrapper, final JsonCommand command, final Long userId) {
        return new CommandSource(wrapper.getActionName(), wrapper.getEntityName(), wrapper.getHref(), command.entityId(),
                userId, command.json(), DateTime.now());
    }

    protected CommandSource() {
        //
    }

    private CommandSource(final String actionName, final String entityName, final String href, final String resourceId,
                          final Long userId, final String commandSerializedAsJson, final DateTime madeOnDateTime) {
        this.actionName = actionName;
        this.entityName = entityName;
        this.resourceGetUrl = href;
        this.resourceId = resourceId;
        this.commandAsJson = commandSerializedAsJson;
        this.userId = userId;
        this.createdOnDate = madeOnDateTime.toDate();
        this.processingResult = CommandProcessingResultType.PROCESSED.getValue();
    }
    
    public void markAsChecked(final DateTime checkedOnDate) {
        this.checkedOnDate = checkedOnDate.toDate();
        this.processingResult = CommandProcessingResultType.PROCESSED.getValue();
    }

    public void markAsRejected(final DateTime checkedOnDate){
        this.checkedOnDate = checkedOnDate.toDate();
        this.processingResult = CommandProcessingResultType.REJECTED.getValue();
    }

    public void updateResourceId(final String resourceId) {
        this.resourceId = resourceId;
    }

    public void updateJsonTo(final String json) {
        this.commandAsJson = json;
    }

    public String resourceId() {
        return this.resourceId;
    }

    public boolean hasJson() {
        return StringUtils.isNotBlank(this.commandAsJson);
    }

    public String json() {
        return this.commandAsJson;
    }

    public String getActionName() {
        return this.actionName;
    }

    public String getEntityName() {
        return this.entityName;
    }

    public String getPermissionCode() {
        return this.actionName + "_" + this.entityName;
    }

    public String getResourceId() {
        return this.resourceId;
    }

    public void updateForAudit(final Long userId, final String transactionId) {
        this.userId = userId;
    }

    public String getResourceGetUrl() {
        return this.resourceGetUrl;
    }

    public Long getUserId() {
        return this.userId;
    }
}