package com.example.HealthCareSystem.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class DoctorWorkingHours {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String workingHoursId;

    Integer dayOfWeek; // 1 for Monday, 2 for Tuesday, ..., 7 for Sunday
    LocalDateTime startTime; // Start time of the working hours
    LocalDateTime endTime; // End time of the working hours
    String isAvailable; // "yes" or "no" to indicate availability
    LocalDateTime createdAt; // Timestamp when the working hours were created

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "doctor_id", referencedColumnName = "doctorId")
    Doctor doctor;
}
