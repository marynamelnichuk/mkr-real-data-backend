package com.mkr.backend.dto;

import lombok.Data;

@Data
public class UserDto {

    private Integer id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

}
