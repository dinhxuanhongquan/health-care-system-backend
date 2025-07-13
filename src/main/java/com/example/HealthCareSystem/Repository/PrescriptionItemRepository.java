package com.example.HealthCareSystem.Repository;

import com.example.HealthCareSystem.Entity.PrescriptionItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionItemRepository extends JpaRepository<PrescriptionItem, String> {
}
