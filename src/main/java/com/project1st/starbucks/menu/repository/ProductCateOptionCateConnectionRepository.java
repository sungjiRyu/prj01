package com.project1st.starbucks.menu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project1st.starbucks.menu.entity.ProductCateOptionCateConnectionEntity;

@Repository
public interface ProductCateOptionCateConnectionRepository extends JpaRepository<ProductCateOptionCateConnectionEntity, Long> {
    
}
