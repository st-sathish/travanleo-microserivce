package com.travanleo.comment.data;

import java.util.Map;

/**
 * Represents the successful result of an REST API call that results in
 * processing a command.
 */
public class CommandProcessingResultBuilder {

    private String commandId;
    private String resourceIdentifier;
    private String entityId;
    private String commentId;
    private Map<String, Object> changes;

    public CommandProcessingResult build() {
        return CommandProcessingResult.fromDetails(this.commandId,
                this.resourceIdentifier, this.entityId, this.changes);
    }

    public CommandProcessingResultBuilder withCommandId(final String withCommandId) {
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

    public CommandProcessingResultBuilder withCommentId(final String withCommentId) {
        this.commentId = withCommentId;
        return this;
    }

    public CommandProcessingResultBuilder withEntityId(final String withEntityId) {
        this.entityId = withEntityId;
        return this;
    }
}