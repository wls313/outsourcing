package com.outsourcing.repository;

import com.outsourcing.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    default User findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "일치하는 정보를 찾을 수 없습니다."));
    }

    List<User> findByRole(String role);

    Optional<User> findByEmail(String email);

}
