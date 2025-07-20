package com.example.HealthCareSystem.Dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ImageProductRes {
    String imageId;
    Set<String> images; // List of Base64 encoded images
    MedicalProductRes medicalProduct; // Reference to the associated medical product
}
