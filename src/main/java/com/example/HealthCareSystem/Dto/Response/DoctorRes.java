package com.example.HealthCareSystem.Dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DoctorRes {
    String doctorId;
    String fullName;
    String biography;
    String experience;
    String education;
    String certifications;
    Boolean isActive;
    Double rating;
    Integer totalViews;
    String isAvailable;
    LocalDateTime dob;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    UserRes user;
}
