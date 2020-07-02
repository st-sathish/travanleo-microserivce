package com.travanleo.comment.data;

import com.google.gson.JsonElement;
import com.travanleo.core.serialization.FromJsonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentDataValidator {

    private FromJsonHelper fromApiJsonHelper;

    @Autowired
    public CommentDataValidator(final FromJsonHelper fromApiJsonHelper) {
        this.fromApiJsonHelper = fromApiJsonHelper;
    }

    public void validateCreateComment(final String command) {
       final JsonElement jsonElement = fromApiJsonHelper.parse(command);
    }

    public void validateUpdateComment(final String command) {
        final JsonElement jsonElement = fromApiJsonHelper.parse(command);
    }
}
