package com.travanleo.user.commands.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.travanleo.core.domain.AbstractPersistableCustom;
import com.travanleo.user.api.JsonCommand;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

@Entity
@Table(name = "command_source")
public class CommandSource extends AbstractPersistableCustom<Long> {

    @Column(name = "action_name", nullable = true, length = 100)
    private String actionName;

    @Column(name = "entity_name", nullable = true, length = 100)
    private String entityName;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "api_get_url", length = 100)
    private String resourceGetUrl;

    @Column(name = "resource_id")
    private Long resourceId;

    @Column(name = "command_as_json", length = 1000)
    private String commandAsJson;

    @Column(name = "created_on_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOnDate;

    @Column(name = "processing_result_enum", nullable = false)
    private Integer processingResult;

    @Column(name = "checked_on_date", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkedOnDate;

    public static CommandSource fullEntryFrom(final CommandWrapper wrapper, final JsonCommand command, final Long userId) {
        return new CommandSource(wrapper.getActionName(), wrapper.getEntityName(), wrapper.getHref(), command.entityId(),
                userId, command.json(), DateTime.now());
    }

    protected CommandSource() {
        //
    }

    private CommandSource(final String actionName, final String entityName, final String href, final Long resourceId,
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

    public void updateResourceId(final Long resourceId) {
        this.resourceId = resourceId;
    }

    public void updateJsonTo(final String json) {
        this.commandAsJson = json;
    }

    public Long resourceId() {
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

    public Long getResourceId() {
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