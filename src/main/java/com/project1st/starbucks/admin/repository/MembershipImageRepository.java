package com.project1st.starbucks.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project1st.starbucks.admin.entity.MembershipImageEntity;

@Repository
public interface MembershipImageRepository extends JpaRepository<MembershipImageEntity, Long>{
    public Integer countByCiName(String ciName);
}
