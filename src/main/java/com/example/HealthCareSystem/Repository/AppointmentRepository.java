package com.example.HealthCareSystem.Repository;

import com.example.HealthCareSystem.Entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, String> {
}
