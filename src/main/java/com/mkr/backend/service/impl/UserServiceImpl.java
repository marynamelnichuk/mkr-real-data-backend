package com.mkr.backend.service.impl;

import com.mkr.backend.dto.ApplicationDto;
import com.mkr.backend.dto.UserDto;
import com.mkr.backend.entity.Application;
import com.mkr.backend.entity.User;
import com.mkr.backend.exception.EntityAlreadyExistsException;
import com.mkr.backend.exception.EntityNotFoundException;
import com.mkr.backend.mapper.UserMapper;
import com.mkr.backend.repository.UserRepository;
import com.mkr.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public UserDto get(int userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        User user = userOptional.orElseThrow(() -> new EntityNotFoundException("User with id [%d] was not found".formatted(userId)));

        return userMapper.map(user);
    }

    @Override
    public void create(UserDto userDto) {
        User user = userMapper.map(userDto);

        try {
            userRepository.save(user);
        } catch (RuntimeException ex) {
            throw new EntityAlreadyExistsException("User with email [%s] already exists".formatted(userDto.getEmail()), ex.getCause());
        }
    }

    @Override
    public void delete(int userId) {
        UserDto userDto = get(userId);

        User user = userMapper.map(userDto);
        user.setId(userId);
        userRepository.delete(user);
    }

}
