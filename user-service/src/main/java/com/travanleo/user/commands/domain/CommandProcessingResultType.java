package com.travanleo.user.commands.domain;

public enum CommandProcessingResultType {

    INVALID(0, "commandProcessingResultType.invalid"), //
    PROCESSED(1, "commandProcessingResultType.processed"), //
    REJECTED(3, "commandProcessingResultType.rejected");

    private final Integer value;
    private final String code;

    private CommandProcessingResultType(final Integer value, final String code) {
        this.value = value;
        this.code = code;
    }

    public Integer getValue() {
        return this.value;
    }

    public String getCode() {
        return this.code;
    }

    public static CommandProcessingResultType fromInt(final Integer typeValue) {
        CommandProcessingResultType type = CommandProcessingResultType.INVALID;
        switch (typeValue) {
            case 1:
                type = PROCESSED;
            break;
            case 2:
                type = REJECTED;
            break;
        }
        return type;
    }
}