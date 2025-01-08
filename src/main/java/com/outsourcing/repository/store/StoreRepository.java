package com.outsourcing.repository.store;

import com.outsourcing.common.entity.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
