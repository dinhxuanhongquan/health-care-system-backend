package com.example.HealthCareSystem.Dto.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MedicalRecordsUpdateReq {
    LocalDateTime recordDate;
    String vitalSigns;
    String presentIllness; // Present illness details
    Integer heartRate; // Heart rate in bpm
    String diagnosis;
    String treatment;
    String temperature; // Temperature in Celsius
    String doctorNotes;
    String recommendations;
    Date followUpDate;
    String followUpInstructions;
    Integer severityLevel;
}
