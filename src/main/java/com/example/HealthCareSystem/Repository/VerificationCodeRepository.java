package com.example.HealthCareSystem.Repository;

import com.example.HealthCareSystem.Entity.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode, String> {
}
