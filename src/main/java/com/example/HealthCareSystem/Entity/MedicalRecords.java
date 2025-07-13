package com.example.HealthCareSystem.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class MedicalRecords {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String recordId;

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
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "patient_id", referencedColumnName = "patientId")
    Patient patient;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "doctor_id", referencedColumnName = "doctorId")
    Doctor doctor;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "appointment_id", referencedColumnName = "appointmentId")
    Appointment appointment;
}
