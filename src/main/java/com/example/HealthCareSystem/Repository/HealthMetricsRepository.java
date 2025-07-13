package com.example.HealthCareSystem.Repository;

import com.example.HealthCareSystem.Entity.HealthMetrics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthMetricsRepository extends JpaRepository<HealthMetrics, String> {
}
