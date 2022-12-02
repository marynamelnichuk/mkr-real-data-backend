package com.mkr.backend.service;

import com.mkr.backend.dto.ReportDto;

import java.util.List;

public interface ReportService {

    List<ReportDto> getAllForApplication(int applicationId);

}
