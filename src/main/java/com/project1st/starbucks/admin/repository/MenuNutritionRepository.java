package com.project1st.starbucks.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project1st.starbucks.admin.entity.MenuNutritionEntity;

@Repository
public interface MenuNutritionRepository extends JpaRepository<MenuNutritionEntity, Long>{
    public Integer countByMnMbiSeq (Long mnMbiSeq);
    MenuNutritionEntity findByMnMbiSeq(Long mnMbiSeq);
}
