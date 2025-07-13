package com.example.HealthCareSystem.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Prescriptions {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String prescriptionId;

    Date prescriptionDate;
    String status; // e.g., "active", "completed", "cancelled"
    Double totalCost;
    String pharmacyNote;
    LocalDateTime expiryDate;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "patient_id", referencedColumnName = "patientId")
    Patient patient;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "medical_record_id", referencedColumnName = "recordId")
    MedicalRecords medicalRecord;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "doctor_id", referencedColumnName = "doctorId")
    Doctor doctor;
}
