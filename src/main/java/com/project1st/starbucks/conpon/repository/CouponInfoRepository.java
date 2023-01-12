package com.project1st.starbucks.conpon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project1st.starbucks.conpon.entity.CouponInfoEntity;



@Repository
public interface CouponInfoRepository extends JpaRepository<CouponInfoEntity, Long> {
    
}
