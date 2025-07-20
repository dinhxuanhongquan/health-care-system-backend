package com.example.HealthCareSystem.Dto.Response;

import com.example.HealthCareSystem.Entity.ChatMessage;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatMessageRes {
    Long id;
    String customerName;
    String customerEmail;
    String message;
    String adminEmail;
    String adminReply;
    ChatMessage.ChatStatus status;
    LocalDateTime createdAt;
    LocalDateTime repliedAt;
}
