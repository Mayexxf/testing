package com.example.carx_testing.repositories;

import com.example.carx_testing.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepositories extends JpaRepository<User, UUID> {
    Optional<User> findUserById(UUID id);
}
