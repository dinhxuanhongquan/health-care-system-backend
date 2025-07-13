package com.example.HealthCareSystem.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Appointment {
    @Id
    @GeneratedValue( strategy = GenerationType.UUID)
    String appointmentId;

    LocalDateTime appointmentDate;
    Integer duration; // in minutes
    String status; // e.g., "Scheduled", "Completed", "Cancelled"
    String type; // e.g., "In-person", "Telemedicine"
    String reasonForVisit;
    String notes;
    String symptomsDescription;
    String reminderSent; // e.g., "Sent", "Not Sent"
    Boolean paymentStatus; // e.g., true for paid, false for unpaid
    Boolean paymentMethod; // e.g., true for online, false for cash
    String cancelledBy; // e.g., "Patient", "Doctor"
    LocalDateTime cancelledAt;
    String cancellationReason;
    LocalDateTime completedAt;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "patient_id", referencedColumnName = "patientId")
    Patient patient;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "doctor_id", referencedColumnName = "doctorId")
    Doctor doctor;
}
