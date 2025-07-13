package com.example.HealthCareSystem.Repository;

import com.example.HealthCareSystem.Entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, String> {
}
