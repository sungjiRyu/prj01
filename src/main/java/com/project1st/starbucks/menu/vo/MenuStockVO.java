package com.project1st.starbucks.menu.vo;

import com.project1st.starbucks.store.entity.StoreMenuConnectEntity;

import lombok.Data;

@Data
public class MenuStockVO {
    private Long menuNo;
    private String name;
    private Integer cost;
    private Integer status;
    private String explain;
    private Integer categoryNo;
    private Integer stock;

    public MenuStockVO(StoreMenuConnectEntity smEntity) {
        this.menuNo = smEntity.getMenu().getMbiSeq();
        this.name = smEntity.getMenu().getMbiName();
        this.cost = smEntity.getMenu().getMbiCost();
        this.status = smEntity.getMenu().getMbiStatus();
        this.explain = smEntity.getMenu().getMbiExplain();
        this.categoryNo = smEntity.getMenu().getMbiPcSeq();
        this.stock = smEntity.getStoreMenuStock();
    }


}
