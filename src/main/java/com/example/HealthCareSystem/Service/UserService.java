package com.example.HealthCareSystem.Service;

import com.example.HealthCareSystem.Constant.PredefinedRole;
import com.example.HealthCareSystem.Dto.Request.UserCreateReq;
import com.example.HealthCareSystem.Dto.Request.UserUpdateMeReq;
import com.example.HealthCareSystem.Dto.Request.UserUpdateReq;
import com.example.HealthCareSystem.Dto.Response.UserRes;
import com.example.HealthCareSystem.Entity.Role;
import com.example.HealthCareSystem.Entity.User;
import com.example.HealthCareSystem.Exception.AppException;
import com.example.HealthCareSystem.Exception.ErrorCode;
import com.example.HealthCareSystem.Mapper.UserMapper;
import com.example.HealthCareSystem.Repository.RoleRepository;
import com.example.HealthCareSystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    @Transactional
    public UserRes create(UserCreateReq req){
        User user = userMapper.toUser(req);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        HashSet<Role> roles = new HashSet<>();
        roleRepository.findById(PredefinedRole.ROlE_DOCTOR).ifPresent(roles::add);
        user.setRoles(roles);
        try{
            user = userRepository.save(user);
        } catch (Exception e) {
            throw new AppException(ErrorCode.USER_NOT_CREATED);
        }
        return userMapper.toUserRes(user);
    }

    @Transactional
    public UserRes getInfor(){
        var context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED));
        return userMapper.toUserRes(user);
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserRes> getAll(){
        return userRepository.findAll().stream()
                .map(userMapper::toUserRes)
                .toList();
    }

    @Transactional
    public UserRes update(UserUpdateReq req, String userId){
        User user = userRepository.findById(userId).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED));
        userMapper.updateUser(user, req);
        if(req.getPassword() != null && !req.getPassword().trim().isEmpty()){
            user.setPassword(passwordEncoder.encode(req.getPassword()));
        }
        if(req.getRoles() != null && !req.getRoles().isEmpty()){
            Set<String> predefinedRoles = PredefinedRole.getAllRoles();
            Set<Role> validRoles = roleRepository.findAllById(req.getRoles()).stream().filter(role -> predefinedRoles.contains(role.getRoleName())).collect(Collectors.toSet());
            if(validRoles.isEmpty()){
                throw new AppException(ErrorCode.ROLE_NOT_EXISTED);
            }
            user.setRoles(validRoles);
        }
        User savedUser = userRepository.save(user);
        return userMapper.toUserRes(savedUser);
    }

    @Transactional
    public UserRes updateMe(UserUpdateMeReq req){
        var context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new AppException(ErrorCode.USER_EXISTED)
        );
        userMapper.updatedMe(user, req);
        User savedUser = userRepository.save(user);
        return userMapper.toUserRes(savedUser);
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(String userId){
        if(!userRepository.existsById(userId)){
            log.warn("User with ID {} does not exist", userId);
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        userRepository.deleteById(userId);
        log.info("User with ID {} deleted successfully", userId);
    }
}
