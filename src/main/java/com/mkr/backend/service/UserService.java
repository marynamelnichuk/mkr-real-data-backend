package com.mkr.backend.service;

import com.mkr.backend.dto.UserDto;

public interface UserService {

    UserDto get(int userId);

    void create(UserDto userDto);

    void delete(int userId);

}