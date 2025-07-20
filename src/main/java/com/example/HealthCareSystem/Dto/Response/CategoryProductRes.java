package com.example.HealthCareSystem.Dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryProductRes {
    String categoryId;
    String categoryName;
    String description;
    String categoryImage;
}
