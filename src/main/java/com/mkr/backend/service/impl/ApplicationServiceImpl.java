package com.mkr.backend.service.impl;

import com.mkr.backend.dto.ApplicationDto;
import com.mkr.backend.dto.UserDto;
import com.mkr.backend.entity.Application;
import com.mkr.backend.entity.User;
import com.mkr.backend.exception.EntityAlreadyExistsException;
import com.mkr.backend.exception.EntityNotFoundException;
import com.mkr.backend.mapper.ApplicationMapper;
import com.mkr.backend.mapper.UserMapper;
import com.mkr.backend.repository.ApplicationRepository;
import com.mkr.backend.service.ApplicationService;
import com.mkr.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final UserService userService;

    private final ApplicationRepository applicationRepository;

    private final ApplicationMapper applicationMapper;
    private final UserMapper userMapper;

    @Override
    public ApplicationDto get(int applicationId) {
        Optional<Application> applicationOptional = applicationRepository.findById(applicationId);

        Application application = applicationOptional.orElseThrow(() -> new EntityNotFoundException("Application with id [%d] was not found".formatted(applicationId)));

        return applicationMapper.map(application);
    }

    @Override
    public List<ApplicationDto> getAllForUser(int userId) {
        List<Application> applicationOptional = applicationRepository.findByUserId(userId);

        return applicationOptional.stream()
                .map(applicationMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public ApplicationDto create(ApplicationDto applicationDto) {
        Application application = applicationMapper.map(applicationDto);

        int userId = applicationDto.getUserId();

        UserDto userDto = userService.get(userId);

        User user = userMapper.map(userDto);
        user.setId(userId);
        application.setUser(user);
        application.setCreationDate(LocalDateTime.now());

        try {
            applicationRepository.save(application);
        } catch (RuntimeException ex) {
            throw new EntityAlreadyExistsException("Application with id [%s] already exists".formatted(applicationDto.getId()), ex.getCause());
        }

        return applicationMapper.map(application);
    }

    @Override
    public void delete(int applicationId) {
        ApplicationDto applicationDto = get(applicationId);

        Application application = applicationMapper.map(applicationDto);
        application.setId(applicationId);
        applicationRepository.delete(application);
    }

}
