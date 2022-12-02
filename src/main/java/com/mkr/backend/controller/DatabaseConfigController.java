package com.mkr.backend.controller;

import com.mkr.backend.dto.DatabaseConfigDto;
import com.mkr.backend.service.DatabaseConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/database-configs")
public class DatabaseConfigController {

    private final DatabaseConfigService databaseConfigService;

    @GetMapping(path = "/{userId}", produces = "application/json")
    public ResponseEntity<List<DatabaseConfigDto>> get(@PathVariable("userId") int userId) {
        return ResponseEntity.ok(databaseConfigService.getAllForUser(userId));
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<DatabaseConfigDto> create(@RequestBody DatabaseConfigDto databaseConfig) {
        return ResponseEntity.ok(databaseConfigService.create(databaseConfig));
    }

    @DeleteMapping(path = "/{databaseConfigId}")
    public ResponseEntity<Void> delete(@PathVariable("databaseConfigId") int databaseConfigId) {
        databaseConfigService.delete(databaseConfigId);

        return ResponseEntity.ok().build();
    }

}
