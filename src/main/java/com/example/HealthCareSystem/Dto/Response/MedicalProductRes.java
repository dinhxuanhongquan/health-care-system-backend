package com.example.HealthCareSystem.Dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MedicalProductRes {
    String productId;
    String productName;
    String description;
    String imagePreview; // Base64 encoded image
    String instructions; // Usage instructions or guidelines
    String warming;
    Double unitPrice; // Price per unit
    LocalDateTime createdAt;
    CategoryProductRes category; // Reference to the associated category
}
