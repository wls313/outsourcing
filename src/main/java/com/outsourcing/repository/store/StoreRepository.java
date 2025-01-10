package com.outsourcing.repository.store;

import com.outsourcing.common.entity.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StoreRepository extends JpaRepository<Store, Long> {

    @Query("select count(s) from Store s where s.user.id = :userId")
    Long countStoreByUser(@Param("userId") Long userId);
}
