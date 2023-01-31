package com.project1st.starbucks.rank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project1st.starbucks.rank.entity.RealRankingEntity;

@Repository
public interface RealRankingRepository extends JpaRepository<RealRankingEntity, Long>{
    
}
