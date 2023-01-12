package com.project1st.starbucks.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project1st.starbucks.store.entity.StoreBasicInfoEntity;

@Repository
public interface StoreBasicInfoRepository extends JpaRepository<StoreBasicInfoEntity, Long> {
    StoreBasicInfoEntity findBySbiBranchName(String branchName);
}
