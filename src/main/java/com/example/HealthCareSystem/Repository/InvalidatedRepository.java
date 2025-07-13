package com.example.HealthCareSystem.Repository;

import com.example.HealthCareSystem.Entity.InvalidatedToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvalidatedRepository extends JpaRepository<InvalidatedToken, String> {
}
