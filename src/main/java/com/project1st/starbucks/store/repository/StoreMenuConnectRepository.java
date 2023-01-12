package com.project1st.starbucks.store.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project1st.starbucks.menu.entity.MenuBasicInfoEntity;
import com.project1st.starbucks.store.entity.StoreBasicInfoEntity;
import com.project1st.starbucks.store.entity.StoreMenuConnectEntity;

@Repository
public interface StoreMenuConnectRepository extends JpaRepository<StoreMenuConnectEntity, Long> {
    Page<StoreMenuConnectEntity> findByStore(StoreBasicInfoEntity store, Pageable pageable);
    StoreMenuConnectEntity findByStoreAndMenu(StoreBasicInfoEntity store, MenuBasicInfoEntity menu);  
    StoreMenuConnectEntity countByStore(StoreBasicInfoEntity store);

}
