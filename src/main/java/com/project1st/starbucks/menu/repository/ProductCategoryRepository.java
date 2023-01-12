package com.project1st.starbucks.menu.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project1st.starbucks.menu.entity.ProductCategoryEntity;



@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategoryEntity, Long> {
    // ProductCategoryEntity findByPcParentSeq(ProductCategoryEntity pcEntity);
    List<ProductCategoryEntity> findByPcParentSeqIsNull();
    List<ProductCategoryEntity> findByPcParentSeq(Long pcParentSeq);
}
