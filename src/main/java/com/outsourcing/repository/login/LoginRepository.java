package com.outsourcing.repository.login;

import com.outsourcing.common.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<User,Long> {
    @Query("SELECT u FROM User u WHERE u.email like :email")
    Optional<User> findByEmail(@Param("email") String email);

}
