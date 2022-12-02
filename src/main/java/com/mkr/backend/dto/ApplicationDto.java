package com.mkr.backend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApplicationDto {

    private Integer id;

    private String name;

    private LocalDateTime creationDate;

    private String description;

    private int userId;

}
