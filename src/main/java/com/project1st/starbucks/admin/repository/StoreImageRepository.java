package com.project1st.starbucks.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project1st.starbucks.admin.entity.StoreImageEntity;

@Repository
public interface StoreImageRepository extends JpaRepository<StoreImageEntity, Long> {
    public Integer countBySiNumber (Long siNumber);
    StoreImageEntity findBySiNumber(Long siNumber);
}
