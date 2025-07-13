package com.example.HealthCareSystem.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class CategoryProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String categoryId;

    String categoryName; // e.g., "Pain Relief", "Antibiotics", "Vitamins"
    String description; // Description of the category

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    String categoryImage;
}
