package com.project1st.starbucks.basket.vo;

import java.util.List;

import lombok.Data;

@Data
public class DetailPageBasketVO {
    private ShoppingBasketVO shoppingBasketVo;
    private List<ShoppingBasketOptionVO> shoppingBasketOption;
}
