package com.example.HealthCareSystem.Dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DoctorWorkingHoursRes {
    String workingHoursId;
    Integer dayOfWeek; // 1 for Monday, 2 for Tuesday, ..., 7 for Sunday
    LocalDateTime startTime; // Start time of the working hours
    LocalDateTime endTime; // End time of the working hours
    String isAvailable; // "yes" or "no" to indicate availability
    LocalDateTime createdAt; // Timestamp when the working hours were created

    DoctorRes doctor;
}
