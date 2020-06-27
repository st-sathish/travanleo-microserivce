package com.travanleo.user.service;

import com.travanleo.core.api.JsonCommand;
import com.travanleo.core.data.CommandProcessingResult;

public interface UserWritePlatformService {

    CommandProcessingResult createUser(final JsonCommand command);

    CommandProcessingResult updateUser(final Long userId, final JsonCommand command);

    CommandProcessingResult deleteUser(final Long userId, final JsonCommand command);
}
