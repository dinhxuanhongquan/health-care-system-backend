package com.example.HealthCareSystem.Dto.Response;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRes {
    String userId;
    String username;
    String password;
    String email;
    String phoneNumber;
    LocalDateTime lastLogin;
    Boolean isActive;
    Set<RoleRes> roles;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    String avatar;
}
