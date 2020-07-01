package com.travanleo.user.service;

import com.travanleo.user.data.UserData;

import java.util.List;

public interface UserReadPlatformService {

    List<UserData> retrieveAll();
}
