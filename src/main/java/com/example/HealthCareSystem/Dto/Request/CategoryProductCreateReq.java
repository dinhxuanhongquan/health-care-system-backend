package com.example.HealthCareSystem.Dto.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryProductCreateReq {
    String categoryName; // e.g., "Pain Relief", "Antibiotics", "Vitamins"
    String description; // Description of the category
    String categoryImage; // Base64 encoded image string or URL to the image
}
