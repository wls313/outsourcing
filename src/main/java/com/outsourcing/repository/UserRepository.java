package com.outsourcing.repository;

import com.outsourcing.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    List<User> findByRole(String role);

    Optional<User> findByEmail(String email);

}
