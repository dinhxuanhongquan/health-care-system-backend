package com.example.HealthCareSystem.Mapper;

import com.example.HealthCareSystem.Dto.Request.ChatMessageReq;
import com.example.HealthCareSystem.Dto.Response.ChatMessageRes;
import com.example.HealthCareSystem.Entity.ChatMessage;
import org.springframework.stereotype.Component;

@Component
public class ChatMessageMapper {
    public ChatMessage toEntity(ChatMessageReq request) {
        ChatMessage entity = new ChatMessage();
        entity.setPatientName(request.getPatientName());
        entity.setPatientEmail(request.getPatientEmail());
        entity.setMessageContent(request.getMessage());
        return entity;
    }

    public ChatMessageRes toResponse(ChatMessage entity) {
        return ChatMessageRes.builder()
                .id(entity.getId())
                .customerName(entity.getPatientName())
                .customerEmail(entity.getPatientEmail())
                .message(entity.getMessageContent())
                .adminEmail(entity.getDoctorEmail())
                .adminReply(entity.getDoctorReply())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .repliedAt(entity.getRepliedAt())
                .build();
    }
}
