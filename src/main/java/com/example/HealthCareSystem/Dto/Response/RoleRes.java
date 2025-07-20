package com.example.HealthCareSystem.Dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleRes {
    String rolesName;
    String description;
    Set<PermissionRes> permissions; // Assuming PermissionRes is another DTO for permissions
}
