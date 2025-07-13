package com.example.HealthCareSystem.Repository;

import com.example.HealthCareSystem.Entity.MedicalProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalProductRepository extends JpaRepository<MedicalProduct, String> {
}
