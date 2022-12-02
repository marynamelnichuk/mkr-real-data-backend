package com.mkr.backend.repository;

import com.mkr.backend.entity.DatabaseConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DatabaseConfigRepository extends JpaRepository<DatabaseConfig, Integer> {

    List<DatabaseConfig> findByApplicationUserId(int userId);

}
