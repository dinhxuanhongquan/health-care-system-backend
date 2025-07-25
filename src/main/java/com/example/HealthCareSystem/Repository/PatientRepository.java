package com.example.HealthCareSystem.Repository;

import com.example.HealthCareSystem.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, String> {
}
