package com.project1st.starbucks.map.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project1st.starbucks.map.entity.StoreGPSEntity;

@Repository
public interface StoreGPSRepository extends JpaRepository<StoreGPSEntity, Long> {
    @Query(value = "SELECT m FROM StoreGPSEntity m WHERE m.sgiTitle LIKE %:keyword%")
     public List<StoreGPSEntity> findGPS(@Param("keyword") String keyword);
}
