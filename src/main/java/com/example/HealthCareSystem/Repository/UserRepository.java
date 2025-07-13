package com.example.HealthCareSystem.Repository;

import com.example.HealthCareSystem.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsUserByUsername(String username);

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
