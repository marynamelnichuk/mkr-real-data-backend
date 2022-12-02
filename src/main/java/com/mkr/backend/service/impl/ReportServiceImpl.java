package com.mkr.backend.service.impl;

import com.mkr.backend.dto.ApplicationDto;
import com.mkr.backend.dto.ReportDto;
import com.mkr.backend.entity.Report;
import com.mkr.backend.mapper.ReportMapper;
import com.mkr.backend.repository.ReportRepository;
import com.mkr.backend.service.ApplicationService;
import com.mkr.backend.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ApplicationService applicationService;

    private final ReportRepository reportRepository;

    private final ReportMapper reportMapper;

    @Override
    public List<ReportDto> getAllForApplication(int applicationId) {
        ApplicationDto applicationDto = applicationService.get(applicationId);

        List<Report> reports = reportRepository.findAllByApplicationId(applicationDto.getId());

        return reports.stream()
                .map(reportMapper::map)
                .collect(Collectors.toList());
    }

}
