package com.example.HealthCareSystem.Dto.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateMeReq {
    String email;
    String phoneNumber;
    Boolean isActive;
    String avatar;
}
