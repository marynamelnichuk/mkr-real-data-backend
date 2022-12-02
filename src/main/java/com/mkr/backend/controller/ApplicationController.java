package com.mkr.backend.controller;

import com.mkr.backend.dto.ApplicationDto;
import com.mkr.backend.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/apps")
public class ApplicationController {

    private final ApplicationService applicationService;

    @GetMapping(path = "/{userId}", produces = "application/json")
    public ResponseEntity<List<ApplicationDto>> get(@PathVariable("userId") int userId) {
        return ResponseEntity.ok(applicationService.getAllForUser(userId));
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<ApplicationDto> create(@RequestBody ApplicationDto application) {
        return ResponseEntity.ok(applicationService.create(application));
    }

    @DeleteMapping(path = "/{appId}")
    public ResponseEntity<Void> delete(@PathVariable("appId") int appId) {
        applicationService.delete(appId);

        return ResponseEntity.ok().build();
    }

}
