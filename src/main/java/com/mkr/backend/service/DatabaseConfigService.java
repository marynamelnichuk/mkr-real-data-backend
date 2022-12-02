package com.mkr.backend.service;

import com.mkr.backend.dto.DatabaseConfigDto;

import java.util.List;

public interface DatabaseConfigService {

    DatabaseConfigDto get(int databaseConfigId);

    List<DatabaseConfigDto> getAllForUser(int userId);

    DatabaseConfigDto create(DatabaseConfigDto databaseConfig);

    void delete(int databaseConfigId);

}
