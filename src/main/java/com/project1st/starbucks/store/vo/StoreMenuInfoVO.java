package com.project1st.starbucks.store.vo;


import com.project1st.starbucks.menu.entity.MenuBasicInfoEntity;
import com.project1st.starbucks.store.entity.StoreBasicInfoEntity;
import com.project1st.starbucks.store.entity.StoreMenuConnectEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreMenuInfoVO {
    private Long smcNo;
    private Integer smcStock;
    private StoreBasicInfoEntity store;
    private MenuBasicInfoEntity menu;

    public StoreMenuInfoVO(StoreMenuConnectEntity entity) {
        this.smcNo = entity.getSmcSeq();
        this.smcStock = entity.getSmcMenuStock();
        this.store = entity.getStore();
        this.menu = entity.getMenu();
    }
}

