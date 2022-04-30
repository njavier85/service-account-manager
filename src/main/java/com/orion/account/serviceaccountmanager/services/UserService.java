package com.orion.account.serviceaccountmanager.services;

import com.orion.account.serviceaccountmanager.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public Optional<List<UserDto>> getAllUsers();

    public Optional<UserDto> getUserbyId(int userId);

}
