package com.example.HealthCareSystem.Dto.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MedicalProductCreateReq {
    String productName;
    String description;
    String imagePreview; // Base64 encoded image
    String instructions; // Usage instructions or guidelines
    String warming;
    Double unitPrice;
    String categoryProductId;
}
