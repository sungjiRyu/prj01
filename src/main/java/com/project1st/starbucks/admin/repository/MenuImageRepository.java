package com.project1st.starbucks.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project1st.starbucks.admin.entity.MenuImageEntity;


@Repository
public interface MenuImageRepository extends JpaRepository<MenuImageEntity, Long>{
    MenuImageEntity findByMiiSeq(Long miiSeq);
}
