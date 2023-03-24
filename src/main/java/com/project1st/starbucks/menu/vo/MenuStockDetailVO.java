package com.project1st.starbucks.menu.vo;

import com.project1st.starbucks.admin.entity.MenuImageEntity;
import com.project1st.starbucks.store.entity.StoreMenuConnectEntity;

import lombok.Data;

@Data
public class MenuStockDetailVO {
    private Long menuNo;
    private String menuName;
    private Integer menuCost;
    private Integer menuStatus;
    private String menuExplain;
    private Long menuCategoryNo;
    private Integer menuStock;
    private String menuImageFile;
    private String menuImageUri;

    public MenuStockDetailVO(StoreMenuConnectEntity smEntity, MenuImageEntity menuImageEntity) {
        this.menuNo = smEntity.getMenu().getMbiSeq();
        this.menuName = smEntity.getMenu().getMbiName();
        this.menuCost = smEntity.getMenu().getMbiCost();
        this.menuStatus = smEntity.getMenu().getMbiStatus();
        this.menuExplain = smEntity.getMenu().getMbiExplain();
        this.menuCategoryNo = smEntity.getMenu().getMbiPcSeq();
        this.menuStock = smEntity.getSmcMenuStock();
        this.menuImageFile = menuImageEntity.getMiiImgFile();
        this.menuImageUri = "http://haeji.mawani.kro.kr:9999/image/menu/" + menuImageEntity.getMiiUri();
    }


}