package com.example.HealthCareSystem.Mapper;

import com.example.HealthCareSystem.Dto.Request.UserCreateReq;
import com.example.HealthCareSystem.Dto.Request.UserUpdateMeReq;
import com.example.HealthCareSystem.Dto.Request.UserUpdateReq;
import com.example.HealthCareSystem.Dto.Response.UserRes;
import com.example.HealthCareSystem.Entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface UserMapper{
    User toUser(UserCreateReq request);

    UserRes toUserRes(User user);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateReq request);

    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "password", ignore = true)
    void updatedMe(@MappingTarget User user, UserUpdateMeReq request);
}
