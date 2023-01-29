package com.project1st.starbucks.membershipcard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project1st.starbucks.membershipcard.entity.MembershipCardImageEntity;

@Repository
public interface MembershipCardImageRepository extends JpaRepository<MembershipCardImageEntity, Long> {
    
}
