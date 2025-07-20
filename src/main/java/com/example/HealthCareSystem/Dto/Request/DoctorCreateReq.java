package com.example.HealthCareSystem.Dto.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DoctorCreateReq {
    String fullName;
    String biography;
    String experience;
    String education;
    String certifications;
    Boolean isActive;
    Double rating;
    Integer totalViews;
    String isAvailable;

    String userId;
}
