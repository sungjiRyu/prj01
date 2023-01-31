package com.project1st.starbucks.menu.vo;

import com.project1st.starbucks.store.entity.StoreMenuConnectEntity;

import lombok.Data;

@Data
public class MenuStockVO {
    private Long menuNo;
    private String menuName;
    private Integer menuCost;
    private Integer menuStatus;
    private String menuExplain;
    private Long menuCategoryNo;
    private Integer menuStock;

    public MenuStockVO(StoreMenuConnectEntity smEntity) {
        this.menuNo = smEntity.getMenu().getMbiSeq();
        this.menuName = smEntity.getMenu().getMbiName();
        this.menuCost = smEntity.getMenu().getMbiCost();
        this.menuStatus = smEntity.getMenu().getMbiStatus();
        this.menuExplain = smEntity.getMenu().getMbiExplain();
        this.menuCategoryNo = smEntity.getMenu().getMbiPcSeq();
        this.menuStock = smEntity.getSmcMenuStock();
    }


}
