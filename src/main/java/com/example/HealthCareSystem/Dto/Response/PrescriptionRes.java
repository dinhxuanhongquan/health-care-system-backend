package com.example.HealthCareSystem.Dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PrescriptionRes {
    String prescriptionId;

    Date prescriptionDate;
    String status; // e.g., "active", "completed", "cancelled"
    Double totalCost;
    String pharmacyNote;
    LocalDateTime expiryDate;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    PatientRes patient; // Reference to the associated patient
    DoctorRes doctor; // Reference to the associated doctor
    MedicalRecordsRes medicalRecord; // Reference to the associated medical record
}
