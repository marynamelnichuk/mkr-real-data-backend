package com.mkr.backend.mapper;

import com.mkr.backend.dto.ApplicationDto;
import com.mkr.backend.entity.Application;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "userId", expression = "java(application.getUser().getId())")
    ApplicationDto map(Application application);

    @Mapping(target = "id", ignore = true)
    Application map(ApplicationDto applicationDto);

}
