package com.mkr.backend.entity;

import com.mkr.backend.entity.enums.DatabaseType;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "database_configs")
public class DatabaseConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "url")
    private String url;

    @Column(name = "database_type")
    @Enumerated(value = EnumType.STRING)
    private DatabaseType databaseType;

    @Column(name = "data_query")
    private String dataQuery;

    @Column(name = "credentials")
    private String credentials;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "application_id", referencedColumnName = "id")
    private Application application;

}
