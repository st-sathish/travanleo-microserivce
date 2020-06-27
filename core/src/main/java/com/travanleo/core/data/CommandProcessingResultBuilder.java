package com.travanleo.core.data;

import java.util.Map;

/**
 * Represents the successful result of an REST API call that results in
 * processing a command.
 */
public class CommandProcessingResultBuilder {

    private Long commandId;
    private String resourceIdentifier;
    private Long entityId;
    private Map<String, Object> changes;
    private boolean rollbackTransaction = false;

    public CommandProcessingResult build() {
        return CommandProcessingResult.fromDetails(this.commandId,
                this.resourceIdentifier, this.entityId, this.changes, this.rollbackTransaction);
    }

    public CommandProcessingResultBuilder withCommandId(final Long withCommandId) {
        this.commandId = withCommandId;
        return this;
    }

    public CommandProcessingResultBuilder with(final Map<String, Object> withChanges) {
        this.changes = withChanges;
        return this;
    }

    public CommandProcessingResultBuilder withResourceIdAsString(final String withResourceIdentifier) {
        this.resourceIdentifier = withResourceIdentifier;
        return this;
    }

    public CommandProcessingResultBuilder withEntityId(final Long withEntityId) {
        this.entityId = withEntityId;
        return this;
    }
    
    public CommandProcessingResultBuilder setRollbackTransaction(final boolean rollbackTransaction) {
        this.rollbackTransaction = this.rollbackTransaction || rollbackTransaction;
        return this;
    }

}