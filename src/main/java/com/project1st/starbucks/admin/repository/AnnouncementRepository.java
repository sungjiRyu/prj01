package com.project1st.starbucks.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project1st.starbucks.admin.entity.AnnouncementEntity;


@Repository
public interface AnnouncementRepository extends JpaRepository<AnnouncementEntity, Long>{
    // AnnouncementEntity findBysaUri(String uri);
    
}
