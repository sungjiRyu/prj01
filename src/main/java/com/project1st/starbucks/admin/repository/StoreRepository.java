package com.project1st.starbucks.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project1st.starbucks.admin.entity.StoreEntity;


@Repository
public interface StoreRepository extends JpaRepository<StoreEntity, Long>{
    public Integer countBySbiBranchName(String sbiBranchName);
    public Integer countBySbiSeq(Long sbiSeq);
}
