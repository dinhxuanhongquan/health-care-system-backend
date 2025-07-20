package com.example.HealthCareSystem.Dto.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PatientUpdateReq {
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
}
