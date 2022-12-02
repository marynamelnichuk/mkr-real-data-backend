package com.mkr.backend.controller;

import com.mkr.backend.dto.UserDto;
import com.mkr.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;

    @GetMapping(path = "/{userId}", produces = "application/json")
    public ResponseEntity<UserDto> get(@PathVariable("userId") int userId) {
        return ResponseEntity.ok(userService.get(userId));
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Void> create(@RequestBody UserDto userDto) {
        userService.create(userDto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<Void> delete(@PathVariable("userId") int userId) {
        userService.delete(userId);

        return ResponseEntity.ok().build();
    }

}
