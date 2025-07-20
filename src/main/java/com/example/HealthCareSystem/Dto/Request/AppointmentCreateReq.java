package com.example.HealthCareSystem.Dto.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppointmentCreateReq {
    LocalDateTime appointmentDate;
    Integer duration; // in minutes
    String type; // e.g., "In-person", "Telemedicine"
    String reasonForVisit;
    String notes;
    String symptomsDescription;
    Boolean paymentMethod; // e.g., true for online, false for cash

    String patientId;
    String doctorId;
}
