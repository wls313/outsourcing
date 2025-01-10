package com.outsourcing.repository.menu;

import com.outsourcing.common.entity.menu.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu,Long> {
}
