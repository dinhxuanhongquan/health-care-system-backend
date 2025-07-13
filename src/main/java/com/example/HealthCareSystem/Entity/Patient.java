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
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String patientId;

    String fullName;
    String bloodType;
    String address;
    Boolean gender;
    String allergies;
    Double height;
    Double weight;
    Double bmi;
    String healthStatus;
    LocalDateTime lastVisit;
    LocalDateTime dob;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    User user;
}
