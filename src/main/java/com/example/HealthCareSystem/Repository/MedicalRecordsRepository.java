package com.example.HealthCareSystem.Repository;

import com.example.HealthCareSystem.Entity.MedicalRecords;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalRecordsRepository extends JpaRepository<MedicalRecords, String> {
}
