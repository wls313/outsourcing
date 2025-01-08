package com.outsourcing.repository.login;

import com.outsourcing.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<User,Long> {

    @Query("SELECT u FROM User u WHERE u.userId like :userId")
    Optional<User> findByUserId(@Param("userId") String userId);

}
