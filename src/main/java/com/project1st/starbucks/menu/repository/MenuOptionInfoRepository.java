package com.project1st.starbucks.menu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project1st.starbucks.menu.entity.MenuBasicInfoEntity;



@Repository
public interface MenuOptionInfoRepository extends JpaRepository<MenuBasicInfoEntity, Long> {
    
}
