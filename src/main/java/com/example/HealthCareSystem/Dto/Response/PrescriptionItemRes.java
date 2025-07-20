package com.example.HealthCareSystem.Dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PrescriptionItemRes {
    String prescriptionItemId;
    Integer quantity;
    Double totalPrice;
    LocalDateTime createdAt;
    PrescriptionRes prescription; // Reference to the associated prescription
    MedicalProductRes medicalProduct; // Reference to the associated medical product
}
