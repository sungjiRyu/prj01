package com.project1st.starbucks.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project1st.starbucks.admin.entity.CouponEntity;



public interface CouponRepository extends JpaRepository<CouponEntity, Long>{
    
}
