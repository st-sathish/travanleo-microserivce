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
    private Long userId;
    private Map<String, Object> changes;

    public CommandProcessingResult build() {
        return CommandProcessingResult.fromDetails(this.commandId,
                this.resourceIdentifier, this.entityId, this.changes);
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

    public CommandProcessingResultBuilder withUserId(final Long withUserId) {
        this.userId = withUserId;
        return this;
    }

    public CommandProcessingResultBuilder withEntityId(final Long withEntityId) {
        this.entityId = withEntityId;
        return this;
    }
}