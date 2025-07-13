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
public class PrescriptionItem {
    @Id
    @GeneratedValue( strategy = GenerationType.UUID)
    String prescriptionItemId;

    Integer quantity;
    Double totalPrice;
    LocalDateTime createdAt;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "prescription_id", referencedColumnName = "prescriptionId")
    Prescriptions prescription;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "product_id", referencedColumnName = "productId")
    MedicalProduct medicalProduct;
}
