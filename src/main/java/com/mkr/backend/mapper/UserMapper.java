package com.mkr.backend.mapper;

import com.mkr.backend.dto.UserDto;
import com.mkr.backend.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", source = "id")
    UserDto map(User user);

    @Mapping(target = "id", ignore = true)
    User map(UserDto use);

}
