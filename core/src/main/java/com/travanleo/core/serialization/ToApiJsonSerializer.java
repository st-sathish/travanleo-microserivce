package com.travanleo.core.serialization;

public interface ToApiJsonSerializer<T> {

    String serialize(Object object);
}