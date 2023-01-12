package com.project1st.starbucks.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project1st.starbucks.admin.entity.MenuEntity;


@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, Long>{
    MenuEntity findByMbiSeq(Long mbiSeq);
    public Integer countByMbiName(String mbiName);
}
