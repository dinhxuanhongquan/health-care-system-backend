package com.example.HealthCareSystem.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String userId;

    String username;
    String password;

    @Column(name = "email", unique = true, columnDefinition = "VARCHAR(255) COLLATE utf8mb4_unicode_ci")
    String email;

    String phoneNumber;
    LocalDateTime lastLogin;

    Boolean idActive;

    @ManyToMany
    Set<Role> roles;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    String avatar;
}
