package com.example.HealthCareSystem.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String patientName;
    String patientEmail;
    String doctorEmail;

    @Column(nullable = false, columnDefinition = "TEXT")
    String messageContent;

    @Column(columnDefinition = "TEXT")
    String doctorReply;

    @Column(nullable = false)
    LocalDateTime createdAt;

    LocalDateTime repliedAt;

    @Enumerated(EnumType.STRING)
    ChatStatus status = ChatStatus.PENDING; // Default status is PENDING

    @PrePersist
    protected void onCreate(){
        createdAt = LocalDateTime.now();
    }

    public enum ChatStatus {
        PENDING,
        IN_PROGRESS,
        COMPLETED
    }
}
