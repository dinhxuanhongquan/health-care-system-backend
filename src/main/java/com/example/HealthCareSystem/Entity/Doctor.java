package com.example.HealthCareSystem.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String doctorId;

    String fullName;
    String biography;
    String experience;
    String education;
    String certifications;
    Boolean isActive;
    Double rating;
    Integer totalViews;
    String isAvailable;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    User user;
}
