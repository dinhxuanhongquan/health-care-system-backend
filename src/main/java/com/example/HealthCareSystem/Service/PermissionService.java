package com.example.HealthCareSystem.Service;

import com.example.HealthCareSystem.Dto.Request.PermissionReq;
import com.example.HealthCareSystem.Dto.Response.PermissionRes;
import com.example.HealthCareSystem.Entity.Permission;
import com.example.HealthCareSystem.Mapper.PermissionMapper;
import com.example.HealthCareSystem.Repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    public PermissionRes create(PermissionReq req){
        // check permissionName
        if(req.getPermissionName() == null || req.getPermissionName().trim().isEmpty()){
            throw new IllegalArgumentException("Permission name cannot be null or empty");
        }
        Permission permission = permissionMapper.toPermission(req);
        permission = permissionRepository.save(permission);
        return permissionMapper.toPermissionRes(permission);
    }

    public List<PermissionRes> getAll(){
        var permissions = permissionRepository.findAll();
        return permissions.stream()
                .map(permissionMapper::toPermissionRes)
                .toList();
    }

    public void delete(String permissionName){
        permissionRepository.deleteById(permissionName);
        log.info("Permission with name {} deleted successfully", permissionName);
    }
}
