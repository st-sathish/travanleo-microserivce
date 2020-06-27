package com.travanleo.core.exception;


import com.travanleo.core.data.ApiParameterError;

import java.util.List;

/**
 * Exception thrown when problem with an API request to the platform.
 */
public class PlatformApiDataValidationException extends RuntimeException {

    private final String globalisationMessageCode;
    private final String defaultUserMessage;
    private final List<ApiParameterError> errors;

    public PlatformApiDataValidationException(final List<ApiParameterError> errors) {
        this.globalisationMessageCode = "validation.msg.validation.errors.exist";
        this.defaultUserMessage = "Validation errors exist.";
        this.errors = errors;
    }

    public PlatformApiDataValidationException(final String globalisationMessageCode, final String defaultUserMessage,
            final List<ApiParameterError> errors) {
        this.globalisationMessageCode = globalisationMessageCode;
        this.defaultUserMessage = defaultUserMessage;
        this.errors = errors;
    }

    public String getGlobalisationMessageCode() {
        return this.globalisationMessageCode;
    }

    public String getDefaultUserMessage() {
        return this.defaultUserMessage;
    }

    public List<ApiParameterError> getErrors() {
        return this.errors;
    }
}