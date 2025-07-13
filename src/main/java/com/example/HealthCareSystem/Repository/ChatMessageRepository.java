package com.example.HealthCareSystem.Repository;

import com.example.HealthCareSystem.Entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, String> {
}
