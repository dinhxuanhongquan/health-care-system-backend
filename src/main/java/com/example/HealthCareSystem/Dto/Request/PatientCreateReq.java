package com.example.HealthCareSystem.Dto.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PatientCreateReq {
    String fullName;
    String bloodType;
    String address;
    Boolean gender;
    String allergies;
    Double height;
    Double weight;
    Double bmi;
    String healthStatus;
    LocalDateTime lastVisit;
    LocalDateTime dob;

    String userId; // Assuming userId is a String, adjust type if necessary
}
