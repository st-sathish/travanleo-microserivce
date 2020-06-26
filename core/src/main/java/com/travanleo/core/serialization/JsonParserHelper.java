package com.travanleo.core.serialization;

import java.util.Set;


import org.apache.commons.lang3.StringUtils;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * Helper class to extract values of json named attributes.
 */
public class JsonParserHelper {

    public boolean parameterExists(final String parameterName, final JsonElement element) {
        if (element == null) { return false; }
        return element.getAsJsonObject().has(parameterName);
    }

    public String extractStringNamed(final String parameterName, final JsonElement element, final Set<String> parametersPassedInRequest) {
        String stringValue = null;
        if (element.isJsonObject()) {
            final JsonObject object = element.getAsJsonObject();
            if (object.has(parameterName) && object.get(parameterName).isJsonPrimitive()) {
                parametersPassedInRequest.add(parameterName);
                final JsonPrimitive primitive = object.get(parameterName).getAsJsonPrimitive();
                final String valueAsString = primitive.getAsString();
                if (StringUtils.isNotBlank(valueAsString)) {
                    stringValue = valueAsString;
                }
            }
        }
        return stringValue;
    }
}