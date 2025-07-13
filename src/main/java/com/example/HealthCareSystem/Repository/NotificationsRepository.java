package com.example.HealthCareSystem.Repository;

import com.example.HealthCareSystem.Entity.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationsRepository extends JpaRepository<Notifications, String> {
}
