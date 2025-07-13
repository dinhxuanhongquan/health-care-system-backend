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
public class MedicalProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String productId;

    String productName;
    String description;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    String imagePreview; // Base64 encoded image

    String instructions; // Usage instructions or guidelines
    String warming;
    Double unitPrice; // Price per unit
    LocalDateTime createdAt;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "category_id", referencedColumnName = "categoryId")
    CategoryProduct categoryProduct;
}
