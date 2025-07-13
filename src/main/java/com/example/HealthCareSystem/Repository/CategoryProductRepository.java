package com.example.HealthCareSystem.Repository;

import com.example.HealthCareSystem.Entity.CategoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryProductRepository extends JpaRepository<CategoryProduct, String> {
}
