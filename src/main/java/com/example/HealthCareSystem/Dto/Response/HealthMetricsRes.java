package com.example.HealthCareSystem.Dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HealthMetricsRes {
    String healthMetricsId;
    String metricType; // e.g., "blood pressure", "heart rate", "weight"
    String value; // e.g., "120/80", "75 bpm", "70 kg"
    String unit; // e.g., "mmHg", "bpm", "kg"
    String note;
    PatientRes patient; // Assuming PatientRes is another DTO for patient details
}
