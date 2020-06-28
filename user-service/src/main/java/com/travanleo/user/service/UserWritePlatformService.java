package com.travanleo.user.service;


import com.travanleo.user.api.JsonCommand;
import com.travanleo.user.data.CommandProcessingResult;

public interface UserWritePlatformService {

    CommandProcessingResult createUser(final JsonCommand command);

    CommandProcessingResult updateUser(final Long userId, final JsonCommand command);

    CommandProcessingResult deleteUser(final Long userId, final JsonCommand command);
}
