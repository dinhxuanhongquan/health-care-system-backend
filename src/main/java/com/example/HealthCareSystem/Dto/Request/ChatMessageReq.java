package com.example.HealthCareSystem.Dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatMessageReq {
    @NotBlank(message = "Customer name is required")
    @Size(max = 100, message = "Customer name must be less than 100 characters")
    String patientName;

    @NotBlank(message = "Customer email is required")
    @Size(message = "Customer email must be less than 100 characters")
    String patientEmail;

    @NotBlank(message = "Message is required")
    @Size(max = 1000, message = "Message must be less than 500 characters")
    String message;
}
