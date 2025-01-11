package com.outsourcing.repository.menu;

import com.outsourcing.common.entity.menu.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu,Long> {

    Menu findByStoreId(Long findStoreById);

    @Query("select m from Menu m where m.store.id = :storeId")
    List<Menu> findMenuByStoreId(Long storeId);
}
