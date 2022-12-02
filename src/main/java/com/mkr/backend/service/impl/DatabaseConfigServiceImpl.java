package com.mkr.backend.service.impl;

import com.mkr.backend.dto.ApplicationDto;
import com.mkr.backend.dto.DatabaseConfigDto;
import com.mkr.backend.entity.Application;
import com.mkr.backend.entity.DatabaseConfig;
import com.mkr.backend.exception.EntityAlreadyExistsException;
import com.mkr.backend.exception.EntityNotFoundException;
import com.mkr.backend.mapper.ApplicationMapper;
import com.mkr.backend.mapper.DatabaseConfigMapper;
import com.mkr.backend.repository.DatabaseConfigRepository;
import com.mkr.backend.service.ApplicationService;
import com.mkr.backend.service.DatabaseConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DatabaseConfigServiceImpl implements DatabaseConfigService {

    private final ApplicationService applicationService;

    private final DatabaseConfigRepository databaseConfigRepository;

    private final DatabaseConfigMapper databaseConfigMapper;
    private final ApplicationMapper applicationMapper;

    @Override
    public DatabaseConfigDto get(int databaseConfigId) {
        Optional<DatabaseConfig> databaseConfigOptional = databaseConfigRepository.findById(databaseConfigId);

        DatabaseConfig databaseConfig = databaseConfigOptional.orElseThrow(() -> new EntityNotFoundException("DatabaseConfig with id [%d] was not found".formatted(databaseConfigId)));

        return databaseConfigMapper.map(databaseConfig);
    }

    @Override
    public List<DatabaseConfigDto> getAllForUser(int userId) {
        List<DatabaseConfig> databaseConfigs = databaseConfigRepository.findByApplicationUserId(userId);

        return databaseConfigs.stream()
                .map(databaseConfigMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public DatabaseConfigDto create(DatabaseConfigDto databaseConfigDto) {
        DatabaseConfig databaseConfig = databaseConfigMapper.map(databaseConfigDto);

        int applicationId = databaseConfigDto.getApplicationId();

        ApplicationDto applicationDto = applicationService.get(applicationId);

        Application application = applicationMapper.map(applicationDto);
        application.setId(applicationId);
        databaseConfig.setApplication(application);

        try {
            databaseConfigRepository.save(databaseConfig);
        } catch (RuntimeException ex) {
            throw new EntityAlreadyExistsException("DatabaseConfig with id [%s] already exists".formatted(databaseConfigDto.getId()), ex.getCause());
        }

        return databaseConfigMapper.map(databaseConfig);
    }

    @Override
    public void delete(int databaseConfigId) {
        DatabaseConfigDto databaseConfigDto = get(databaseConfigId);

        DatabaseConfig databaseConfig = databaseConfigMapper.map(databaseConfigDto);
        databaseConfig.setId(databaseConfigId);
        databaseConfigRepository.delete(databaseConfig);
    }

}


