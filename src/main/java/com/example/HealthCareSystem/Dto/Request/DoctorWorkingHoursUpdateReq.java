package com.example.HealthCareSystem.Dto.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DoctorWorkingHoursUpdateReq {
    Integer dayOfWeek;
    LocalDateTime startTime; // Start time of the working hours
    LocalDateTime endTime; // End time of the working hours
    String isAvailable; // "yes" or "no" to indicate availability

}
