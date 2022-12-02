package com.mkr.backend.mapper;

import com.mkr.backend.dto.DatabaseConfigDto;
import com.mkr.backend.entity.DatabaseConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DatabaseConfigMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "appName", expression = "java(databaseConfig.getApplication().getName())")
    @Mapping(target = "applicationId", expression = "java(databaseConfig.getApplication().getId())")
    @Mapping(target = "databaseType", expression = "java(databaseConfig.getDatabaseType().getValue())")
    DatabaseConfigDto map(DatabaseConfig databaseConfig);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "databaseType", expression = "java(com.mkr.backend.entity.enums.DatabaseType.fromKringe(databaseConfig.getDatabaseType()))")
    DatabaseConfig map(DatabaseConfigDto databaseConfig);

}
