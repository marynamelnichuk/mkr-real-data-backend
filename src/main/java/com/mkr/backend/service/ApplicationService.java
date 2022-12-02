package com.mkr.backend.service;

import com.mkr.backend.dto.ApplicationDto;

import java.util.List;

public interface ApplicationService {

    ApplicationDto get(int applicationId);

    List<ApplicationDto> getAllForUser(int userId);

    ApplicationDto create(ApplicationDto applicationDto);

    void delete(int applicationId);

}
