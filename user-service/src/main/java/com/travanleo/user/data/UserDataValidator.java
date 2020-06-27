package com.travanleo.user.data;

import com.google.gson.JsonElement;
import com.travanleo.core.data.ApiParameterError;
import com.travanleo.core.data.DataValidatorBuilder;
import com.travanleo.core.exception.PlatformApiDataValidationException;
import com.travanleo.core.serialization.FromJsonHelper;
import com.travanleo.user.api.UserApiConstants;
import com.travanleo.user.exception.InvalidJsonException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDataValidator {

    private final FromJsonHelper fromApiJsonHelper;

    @Autowired
    public UserDataValidator(final FromJsonHelper fromApiJsonHelper) {
        this.fromApiJsonHelper = fromApiJsonHelper;
    }

    public void validateForCreate(final String json) {
        if (StringUtils.isBlank(json)) { throw new InvalidJsonException(); }
        final JsonElement element = this.fromApiJsonHelper.parse(json);
        final List<ApiParameterError> dataValidationErrors = new ArrayList<>();
        final DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(dataValidationErrors)
                .resource(UserApiConstants.USER_RESOURCE_NAME);
        if (this.fromApiJsonHelper.parameterExists(UserApiConstants.FIRST_NAME, element)) {
            final String firstName = this.fromApiJsonHelper.extractStringNamed(UserApiConstants.FIRST_NAME, element);
            baseDataValidator.reset().parameter(UserApiConstants.FIRST_NAME).value(firstName).notBlank();
        }
        if (this.fromApiJsonHelper.parameterExists(UserApiConstants.LAST_NAME, element)) {
            final String lastName = this.fromApiJsonHelper.extractStringNamed(UserApiConstants.LAST_NAME, element);
            baseDataValidator.reset().parameter(UserApiConstants.LAST_NAME).value(lastName).notBlank();
        }
        throwExceptionIfValidationWarningsExist(dataValidationErrors);
    }

    private void throwExceptionIfValidationWarningsExist(final List<ApiParameterError> dataValidationErrors) {
        if (!dataValidationErrors.isEmpty()) {
            throw new PlatformApiDataValidationException(dataValidationErrors);
        }
    }
}
