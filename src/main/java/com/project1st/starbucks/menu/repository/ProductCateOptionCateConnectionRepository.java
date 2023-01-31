package com.project1st.starbucks.menu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project1st.starbucks.menu.entity.ProductCateOptionCateConnectionEntity;
import com.project1st.starbucks.menu.entity.ProductCategoryEntity;

@Repository
public interface ProductCateOptionCateConnectionRepository extends JpaRepository<ProductCateOptionCateConnectionEntity, Long> {
    List<ProductCateOptionCateConnectionEntity> findByProductCategory(ProductCategoryEntity pcEntity);
}
