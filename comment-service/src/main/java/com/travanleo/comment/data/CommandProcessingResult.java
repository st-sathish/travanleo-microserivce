package com.travanleo.comment.data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents the successful result of an REST API call that results in
 * processing a command.
 */
public class CommandProcessingResult implements Serializable {

    private String commandId;
    private final Map<String, Object> changes;
    private final String resourceId;
    @SuppressWarnings("unused")
    private final String resourceIdentifier;
    private Boolean rollbackTransaction;

    public static CommandProcessingResult fromDetails(final String commandId, final String resourceIdentifier,
                                                      final String entityId,
                                                      final Map<String, Object> changes) {
        return new CommandProcessingResult(commandId, resourceIdentifier, entityId, changes);
    }

    public static CommandProcessingResult empty() {
        return new CommandProcessingResult(null, null, null, null);
    }

    /*
     * Deprecated
     */
    public CommandProcessingResult(final String entityId) {
        if (entityId != null) {
            this.resourceIdentifier = entityId.toString();
        } else {
            this.resourceIdentifier = null;
        }
        this.resourceId = entityId;
        this.changes = new HashMap<>();
    }

    private CommandProcessingResult(final String commandId, final String resourceIdentifier, final String resourceId,
            final Map<String, Object> changesOnly) {
        this.commandId = commandId;
        this.resourceIdentifier = resourceIdentifier;
        this.changes = changesOnly;
        this.resourceId = resourceId;
    }

    private CommandProcessingResult(final String resourceId, final String commandId, final Map<String, Object> changesOnly) {
        if (resourceId != null) {
            this.resourceIdentifier = resourceId.toString();
        } else {
            this.resourceIdentifier = null;
        }
        this.commandId = commandId;
        this.changes = changesOnly;
        this.resourceId = resourceId;
    }

    public String commandId() {
        return this.commandId;
    }

    public Map<String, Object> getChanges() {
        Map<String, Object> checkIfEmpty = null;
        if (this.changes != null && !this.changes.isEmpty()) {
            checkIfEmpty = this.changes;
        }
        return checkIfEmpty;
    }

    public boolean hasChanges() {
        final boolean noChanges = this.changes == null || this.changes.isEmpty();
        return !noChanges;
    }

    public String getResourceId() {
        return this.resourceId;
    }

    public String getCommandId() {
        return this.commandId;
    }
}