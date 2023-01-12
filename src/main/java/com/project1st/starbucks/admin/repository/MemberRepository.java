package com.project1st.starbucks.admin.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project1st.starbucks.admin.entity.MemberEntity;


@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long>{
    // List<MemberEntity> findByMiId(String miId);
    // Long countByMiId(String miId);
     MemberEntity findByMiSeq(Long miSeq);
    
}
