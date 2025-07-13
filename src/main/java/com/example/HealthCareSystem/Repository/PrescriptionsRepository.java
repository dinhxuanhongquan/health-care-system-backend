package com.example.HealthCareSystem.Repository;

import com.example.HealthCareSystem.Entity.Prescriptions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionsRepository extends JpaRepository<Prescriptions, String> {
}
