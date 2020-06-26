package com.travanleo.core.serialization;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.google.gson.JsonParser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

@Primary
@Component
public class FromJsonHelper {

    private final Gson gsonConverter;
    private final JsonParserHelper helperDelegator;

    public FromJsonHelper() {
        this.gsonConverter = new Gson();
        this.helperDelegator = new JsonParserHelper();
    }

    public Map<String, Boolean> extractMap(final Type typeOfMap, final String json) {
        return this.gsonConverter.fromJson(json, typeOfMap);
    }

    public Map<String, String> extractDataMap(final Type typeOfMap, final String json) {
        return this.gsonConverter.fromJson(json, typeOfMap);
    }

    public Map<String, Object> extractObjectMap(final Type typeOfMap, final String json) {
        return this.gsonConverter.fromJson(json, typeOfMap);
    }

    public <T> T fromJson(final String json, final Class<T> classOfT) {
        return this.gsonConverter.fromJson(json, classOfT);
    }

    public String toJson(final JsonElement jsonElement) {
        return this.gsonConverter.toJson(jsonElement);
    }

    public String toJson(final Object object) {
        return this.gsonConverter.toJson(object);
    }

    public boolean parameterExists(final String parameterName, final JsonElement element) {
        return this.helperDelegator.parameterExists(parameterName, element);
    }

    public String extractStringNamed(final String parameterName, final JsonElement element) {
        return this.helperDelegator.extractStringNamed(parameterName, element, new HashSet<String>());
    }

    public String extractStringNamed(final String parameterName, final JsonElement element, final Set<String> parametersPassedInRequest) {
        return this.helperDelegator.extractStringNamed(parameterName, element, parametersPassedInRequest);
    }

    public Gson getGsonConverter() {
        return this.gsonConverter;
    }

    public JsonElement parse(final String json) {
        JsonElement parsedElement = null;
        if (StringUtils.isNotBlank(json)) {
            parsedElement = JsonParser.parseString(json);
        }
        return parsedElement;
    }
}