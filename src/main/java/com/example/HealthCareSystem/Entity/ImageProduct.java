package com.example.HealthCareSystem.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class ImageProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String imageId;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    List<String> images; // List of Base64 encoded images

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "productId")
    MedicalProduct medicalProduct; // Reference to the associated medical product
}
