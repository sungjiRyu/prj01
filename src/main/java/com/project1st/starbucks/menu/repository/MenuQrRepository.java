package com.project1st.starbucks.menu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project1st.starbucks.menu.entity.MenuQrEntity;

@Repository
public interface MenuQrRepository extends JpaRepository<MenuQrEntity, Long> {
    MenuQrEntity findByMqiMbiSeq(Long mqiMbiSeq);
}
