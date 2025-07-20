package com.example.HealthCareSystem.Dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppointmentRes {
    String appointmentId;
    LocalDateTime appointmentDate;
    Integer duration; // in minutes
    String status; // e.g., "Scheduled", "Completed", "Cancelled"
    String type;
    String reasonForVisit;
    String notes;
    String symptomsDescription;
    String reminderSent;
    Boolean paymentStatus;
    Boolean paymentMethod;
    String cancelledBy;
    LocalDateTime cancelledAt;
    String cancellationReason;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    LocalDateTime completedAt;
    PatientRes patient;
    DoctorRes doctor;
}
