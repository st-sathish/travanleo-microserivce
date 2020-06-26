package com.travanleo.core.data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents the successful result of an REST API call that results in
 * processing a command.
 */
public class CommandProcessingResult implements Serializable {

    private Long commandId;
    private final Long groupId;
    private final Long userId = null;
    private final Long subResourceId;
    private final String transactionId;
    private final Map<String, Object> changes;
    @SuppressWarnings("unused")
    private final String resourceIdentifier;
    private final Long productId;
    private Boolean rollbackTransaction;

    public static CommandProcessingResult fromDetails(final Long commandId, final Long officeId, final Long groupId, final Long clientId,
            final Long loanId, final Long savingsId, final String resourceIdentifier, final Long entityId, final String transactionId,
            final Map<String, Object> changes, final Long productId, final Boolean rollbackTransaction, final Long subResourceId) {
        return new CommandProcessingResult(commandId, officeId, groupId, clientId, loanId, savingsId, resourceIdentifier, entityId,
                transactionId, changes, productId, rollbackTransaction, subResourceId);
    }

    public static CommandProcessingResult commandOnlyResult(final Long commandId) {
        return new CommandProcessingResult(null, null, commandId, null);
    }

    public static CommandProcessingResult resourceResult(final Long resourceId, final Long commandId) {
        return new CommandProcessingResult(resourceId, null, commandId, null);
    }

    public static CommandProcessingResult resourceResult(final Long resourceId, final Long commandId, final Map<String, Object> changes) {
        return new CommandProcessingResult(resourceId, null, commandId, changes);
    }

    public static CommandProcessingResult subResourceResult(final Long resourceId, final Long subResourceId, final Long commandId) {
        return new CommandProcessingResult(resourceId, subResourceId, commandId, null);
    }

    public static CommandProcessingResult subResourceResult(final Long resourceId, final Long subResourceId, final Long commandId,
            final Map<String, Object> changes) {
        return new CommandProcessingResult(resourceId, subResourceId, commandId, changes);
    }

    public static CommandProcessingResult withChanges(final Long resourceId, final Map<String, Object> changes) {
        return new CommandProcessingResult(resourceId, null, null, changes);
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
        this.groupId = null;
        this.transactionId = null;
        this.changes = new HashMap<>();
        this.productId = null;
        this.subResourceId = null;
    }

    private CommandProcessingResult(final Long commandId, final Long officeId, final Long groupId, final Long clientId, final Long loanId,
            final Long savingsId, final String resourceIdentifier, final Long resourceId, final String transactionId,
            final Map<String, Object> changesOnly, final Long productId, Boolean rollbackTransaction, final Long subResourceId) {
        this.commandId = commandId;
        this.groupId = groupId;
        this.resourceIdentifier = resourceIdentifier;
        this.changes = changesOnly;
        this.transactionId = transactionId;
        this.productId = productId;
        this.rollbackTransaction = rollbackTransaction;
        this.subResourceId = subResourceId;
    }

    private CommandProcessingResult(final Long resourceId, final Long officeId, final Long commandId, final Map<String, Object> changesOnly) {
        if (resourceId != null) {
            this.resourceIdentifier = resourceId.toString();
        } else {
            this.resourceIdentifier = null;
        }
        this.groupId = null;
        this.transactionId = null;
        this.commandId = commandId;
        this.changes = changesOnly;
        this.productId = null;
        this.subResourceId = null;
    }

    public Long commandId() {
        return this.commandId;
    }

    public Long getGroupId() {
        return this.groupId;
    }

    public String getTransactionId() {
        return this.transactionId;
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

    public Long getProductId() {
        return this.productId;
    }

    public boolean isRollbackTransaction() {
        return this.rollbackTransaction != null && this.rollbackTransaction;
    }

    public void setRollbackTransaction(Boolean rollbackTransaction) {
        this.rollbackTransaction = rollbackTransaction;
    }

    public Long getSubResourceId() {
        return subResourceId;
    }
}