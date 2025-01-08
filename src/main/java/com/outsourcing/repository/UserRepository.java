package com.outsourcing.repository;

import com.outsourcing.common.entity.User;
import com.outsourcing.common.exception.IdNotFoundExcetion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    default User findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new IdNotFoundExcetion("아이디를 확인해주세요"));
    }

    List<User> findByRole(String role);

    Optional<User> findByEmail(String email);

}
