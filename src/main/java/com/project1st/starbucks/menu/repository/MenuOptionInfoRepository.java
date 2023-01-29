package com.project1st.starbucks.menu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project1st.starbucks.menu.entity.MenuOptionInfoEntity;



@Repository
public interface MenuOptionInfoRepository extends JpaRepository<MenuOptionInfoEntity, Long> {
    List<MenuOptionInfoEntity> findAllByMoiMocSeq(Long optionCateSeq);
}
