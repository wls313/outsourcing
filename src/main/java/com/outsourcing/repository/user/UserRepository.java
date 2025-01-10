package com.outsourcing.repository.user;

import com.outsourcing.common.entity.user.User;
import com.outsourcing.common.exception.IdNotFoundExcetion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.name = :memberName")
    Optional<User> findByName(@Param("memberName") String memberName);

    default User findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new IdNotFoundExcetion("아이디를 확인해주세요"));
    }

    List<User> findByRole(String role);

    Optional<User> findByEmail(String email);
}
