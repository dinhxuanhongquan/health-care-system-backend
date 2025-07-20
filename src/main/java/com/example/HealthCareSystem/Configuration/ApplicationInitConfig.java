package com.example.HealthCareSystem.Configuration;

import com.example.HealthCareSystem.Constant.PredefinedRole;
import com.example.HealthCareSystem.Entity.Role;
import com.example.HealthCareSystem.Entity.User;
import com.example.HealthCareSystem.Repository.RoleRepository;
import com.example.HealthCareSystem.Repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;

    @NonFinal
    static final String ADMIN_USER_NAME = "admin";

    @NonFinal
    static final String ADMIN_PASSWORD = "admin";

    @Bean
    @ConditionalOnProperty(
            prefix = "spring",
            value = "datasource.driverClassName",
            havingValue = "com.mysql.cj.jdbc.Driver"
    )
    ApplicationRunner applicationRunner(UserRepository userRepository, RoleRepository roleRepository){
        log.info("Initializing application >>>");
        return args -> {
            if(userRepository.findByUsername(ADMIN_USER_NAME).isEmpty()){
                roleRepository.save(Role.builder()
                        .roleName(PredefinedRole.ROLE_PATIENT)
                        .description("Patient role")
                        .build());

                roleRepository.save(Role.builder()
                        .roleName(PredefinedRole.ROlE_DOCTOR)
                        .description("Doctor role")
                        .build());

                Role adminRole = roleRepository.save(Role.builder()
                        .roleName(PredefinedRole.ROLE_ADMIN)
                        .description("Admin role")
                        .build());

                var roles = new HashSet<Role>();
                roles.add(adminRole);

                User user = User.builder()
                        .username(ADMIN_USER_NAME)
                        .password(passwordEncoder.encode(ADMIN_PASSWORD))
                        .roles(roles)
                        .build();
                user = userRepository.save(user);

                log.warn("Admin user has been created with username: {} and password: {}", ADMIN_USER_NAME, ADMIN_PASSWORD);

            }
            log.info("Application initialization completed <<<");
        };
    }
}
