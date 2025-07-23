package com.example.HealthCareSystem.Mapper;

import com.example.HealthCareSystem.Dto.Request.NotificationsCreateReq;
import com.example.HealthCareSystem.Dto.Request.NotificationsUpdateReq;
import com.example.HealthCareSystem.Dto.Response.NotificationsRes;
import com.example.HealthCareSystem.Entity.Notifications;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface NotificationsMapper {
    Notifications toNotifications(NotificationsCreateReq req);

    NotificationsRes toNotificationsRes(Notifications notifications);

    @Mapping(target = "createdAt", ignore = true)
    void updateNotifications(@MappingTarget Notifications notifications, NotificationsUpdateReq req);
}
