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
public class Notifications {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String notificationId;

    String title;
    String message;
    String data;
    Boolean isRead;
    Integer priority; // 0: low, 1: medium, 2: high
    LocalDateTime expiresAt;
    LocalDateTime createdAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    User user; // Reference to the user who receives the notification
}
