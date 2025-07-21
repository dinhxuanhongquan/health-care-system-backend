package com.example.HealthCareSystem.Mapper;

import com.example.HealthCareSystem.Dto.Request.PermissionReq;
import com.example.HealthCareSystem.Dto.Response.PermissionRes;
import com.example.HealthCareSystem.Entity.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    @Mapping(target = "permissionName", source = "permissionName")
    @Mapping(target = "description", source = "description")
    Permission toPermission(PermissionReq request);

    PermissionRes toPermissionRes(Permission permission);
}
