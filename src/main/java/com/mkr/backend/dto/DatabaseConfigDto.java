package com.mkr.backend.dto;

import lombok.Data;

@Data
public class DatabaseConfigDto {

    private Integer id;

    private String appName;

    private String url;

    private String databaseType;

    private String dataQuery;

    private String credentials;

    private int applicationId;

}
