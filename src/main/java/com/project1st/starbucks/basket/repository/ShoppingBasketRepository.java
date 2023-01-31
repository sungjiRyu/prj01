package com.project1st.starbucks.basket.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project1st.starbucks.basket.entity.ShoppingBasketEntity;
import com.project1st.starbucks.store.entity.StoreMenuConnectEntity;


public interface ShoppingBasketRepository extends JpaRepository<ShoppingBasketEntity, Long> {
    public List<ShoppingBasketEntity> findBySbMiSeqAndSbStatus(Long sbMiSeq, Long sbStatus);
    Optional<ShoppingBasketEntity> findBySbMiSeq(Long sbMiSeq);
    ShoppingBasketEntity findBySbMiSeqAndStoreMenuConnect(Long sbMiseq, StoreMenuConnectEntity storemenuconnect);
    ShoppingBasketEntity findBySbMiSeqAndStoreMenuConnectAndSbStatus(Long sbMiseq, StoreMenuConnectEntity storemenuconnect, Long sbStatus);
    ShoppingBasketEntity findBySbMiSeqAndStoreMenuConnectAndSbStatusAndSbOrderNumber(Long sbMiseq, StoreMenuConnectEntity storemenuconnect, Long sbStatus, Long sbOrderNumber);
}
