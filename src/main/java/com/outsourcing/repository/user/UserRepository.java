package com.outsourcing.repository.user;

import com.outsourcing.common.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long id);

    List<User> findByRole(String role);

    Optional<User> findByEmail(String email);
}
