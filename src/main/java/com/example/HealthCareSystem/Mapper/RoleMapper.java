package com.example.HealthCareSystem.Mapper;

import com.example.HealthCareSystem.Dto.Request.RoleReq;
import com.example.HealthCareSystem.Dto.Response.RoleRes;
import com.example.HealthCareSystem.Entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PermissionMapper.class})
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleReq request);

    RoleRes toRoleRes(Role role);
}
