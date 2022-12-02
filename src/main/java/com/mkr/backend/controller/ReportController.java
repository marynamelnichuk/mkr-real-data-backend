package com.mkr.backend.controller;

import com.mkr.backend.dto.ReportDto;
import com.mkr.backend.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/reports")
public class ReportController {

    private final ReportService reportService;

    @GetMapping(path = "/{appId}", produces = "application/json")
    public ResponseEntity<List<ReportDto>> get(@PathVariable("appId") int appId) {
        return ResponseEntity.ok(reportService.getAllForApplication(appId));
    }

}
