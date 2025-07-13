package com.example.HealthCareSystem.Repository;

import com.example.HealthCareSystem.Entity.DoctorWorkingHours;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorWorkingHoursRepository extends JpaRepository<DoctorWorkingHours, String> {
    // Additional query methods can be defined here if needed
}
