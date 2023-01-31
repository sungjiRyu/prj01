package com.project1st.starbucks.basket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project1st.starbucks.basket.entity.ShoppingBasketEntity;
import com.project1st.starbucks.basket.entity.ShoppingBasketOptionEntity;

public interface ShoppingBasketAddRepository extends JpaRepository<ShoppingBasketOptionEntity, Long>{
    ShoppingBasketOptionEntity findByShoppingBasket(ShoppingBasketEntity shoppingBasket);
    List<ShoppingBasketOptionEntity> findBySboOptionOrderNumber(Integer sboOptionOrderNumber);
}