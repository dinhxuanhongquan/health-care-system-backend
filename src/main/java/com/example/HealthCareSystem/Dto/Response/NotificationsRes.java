package com.example.HealthCareSystem.Dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationsRes {
    String notificationId;
    String title;
    String message;
    String data;
    Boolean isRead;
    Integer priority; // 0: low, 1: medium, 2: high
    LocalDateTime expiresAt;
    LocalDateTime createdAt;

    UserRes user;
}
