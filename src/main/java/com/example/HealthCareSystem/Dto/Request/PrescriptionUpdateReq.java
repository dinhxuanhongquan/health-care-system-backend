package com.example.HealthCareSystem.Dto.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PrescriptionUpdateReq {
    Date prescriptionDate;
    String status; // e.g., "active", "completed", "cancelled"
    Double totalCost;
    String pharmacyNote;

    String medicalRecordId;
    String doctorId;
    String patientId;
}
