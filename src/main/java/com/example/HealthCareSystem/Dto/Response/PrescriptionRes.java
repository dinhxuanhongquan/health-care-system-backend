package com.example.HealthCareSystem.Dto.Response;

import java.time.LocalDateTime;
import java.util.Date;

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
