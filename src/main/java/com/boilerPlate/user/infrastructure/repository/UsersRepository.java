package com.boilerPlate.user.infrastructure.repository;

import com.boilerPlate.user.infrastructure.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUserId(String userId);

    boolean existsByUserId(String userId);
}
