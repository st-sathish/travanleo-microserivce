package com.travanleo.user.data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents the successful result of an REST API call that results in
 * processing a command.
 */
public class CommandProcessingResult implements Serializable {

    private Long commandId;
    private final Map<String, Object> changes;
    private final Long resourceId;
    @SuppressWarnings("unused")
    private final String resourceIdentifier;
    private Boolean rollbackTransaction;

    public static CommandProcessingResult fromDetails(final Long commandId, final String resourceIdentifier,
                                                      final Long entityId,
                                                      final Map<String, Object> changes) {
        return new CommandProcessingResult(commandId, resourceIdentifier, entityId, changes);
    }

    public static CommandProcessingResult empty() {
        return new CommandProcessingResult(null, null, null, null);
    }

    /*
     * Deprecated
     */
    public CommandProcessingResult(final Long entityId) {
        if (entityId != null) {
            this.resourceIdentifier = entityId.toString();
        } else {
            this.resourceIdentifier = null;
        }
        this.resourceId = entityId;
        this.changes = new HashMap<>();
    }

    private CommandProcessingResult(final Long commandId, final String resourceIdentifier, final Long resourceId,
            final Map<String, Object> changesOnly) {
        this.commandId = commandId;
        this.resourceIdentifier = resourceIdentifier;
        this.changes = changesOnly;
        this.resourceId = resourceId;
    }

    private CommandProcessingResult(final Long resourceId, final Long commandId, final Map<String, Object> changesOnly) {
        if (resourceId != null) {
            this.resourceIdentifier = resourceId.toString();
        } else {
            this.resourceIdentifier = null;
        }
        this.commandId = commandId;
        this.changes = changesOnly;
        this.resourceId = resourceId;
    }

    public Long commandId() {
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

    public Long getResourceId() {
        return this.resourceId;
    }

    public Long getCommandId() {
        return this.commandId;
    }
}