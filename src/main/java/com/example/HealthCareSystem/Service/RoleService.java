package com.example.HealthCareSystem.Service;

import com.example.HealthCareSystem.Dto.Request.RoleReq;
import com.example.HealthCareSystem.Dto.Response.RoleRes;
import com.example.HealthCareSystem.Mapper.RoleMapper;
import com.example.HealthCareSystem.Repository.PermissionRepository;
import com.example.HealthCareSystem.Repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    RoleRepository repository;
    PermissionRepository permissionRepository;
    RoleMapper mapper;

    public RoleRes create(RoleReq req){
        var role = mapper.toRole(req);

        var permission = permissionRepository.findAllById(req.getPermissions());
        role.setPermissions(new HashSet<>(permission));

        role = repository.save(role);
        return mapper.toRoleRes(role);
    }

    @Transactional(readOnly = true)
    public List<RoleRes> getAll() {
        return repository.findAll().stream()
                .map(mapper::toRoleRes)
                .toList();
    }

    public void delete(String roleName){
        if (!repository.existsById(roleName)) {
            log.warn("Role with name {} does not exist", roleName);
            return;
        }
        repository.deleteById(roleName);
        log.info("Role with name {} deleted successfully", roleName);
    }
}

