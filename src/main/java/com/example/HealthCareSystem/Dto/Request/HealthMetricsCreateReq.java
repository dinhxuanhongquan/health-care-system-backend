package com.example.HealthCareSystem.Dto.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HealthMetricsCreateReq {
    String metricType; // e.g., "blood pressure", "heart rate", "weight"
    String value; // e.g., "120/80", "75 bpm", "70 kg"
    String unit; // e.g., "mmHg", "bpm", "kg"
    String note; // Additional notes about the health metric

    String patientId; // ID of the patient to whom this health metric belongs
}
