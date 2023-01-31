package com.project1st.starbucks.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project1st.starbucks.admin.entity.EventEntity;



@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long>{
    public Integer countByEvSeq(Long evSeq);
    EventEntity findByEvSeq(Long evSeq);
}
