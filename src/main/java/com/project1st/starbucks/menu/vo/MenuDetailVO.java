package com.project1st.starbucks.menu.vo;

import com.project1st.starbucks.admin.entity.MenuImageEntity;
import com.project1st.starbucks.admin.entity.MenuNutritionEntity;
import com.project1st.starbucks.menu.entity.MenuQrEntity;
import com.project1st.starbucks.store.entity.StoreMenuConnectEntity;

import lombok.Data;

@Data
public class MenuDetailVO {
    private Long menuNo;
    private String menuName;
    private Integer menuCost;
    private Integer menuStatus;
    private String menuExplain;
    private Long menuCategorySeq;
    private String menuFile;
    private String menuUri;
    private String menuQrFile;
    private String menuQrUri;
    private String menuNutritionFile;
    private String menuNutritionUri;

    public MenuDetailVO() {}
    public MenuDetailVO(MenuImageEntity image, MenuQrEntity qr, MenuNutritionEntity nutrition) {
        this.menuNo = image.getMiiNumber().getMbiSeq();
        this.menuName = image.getMiiNumber().getMbiName();
        this.menuCost = image.getMiiNumber().getMbiCost();
        this.menuStatus = image.getMiiNumber().getMbiStatus();
        this.menuExplain = image.getMiiNumber().getMbiExplain();
        this.menuCategorySeq = image.getMiiNumber().getMbiPcSeq();
        this.menuFile = image.getMiiImgFile();
        this.menuUri = "http://haeji.mawani.kro.kr:9999/image/menu/" + image.getMiiUri();
        this.menuQrFile = qr.getMqiImageFile();
        this.menuQrUri = "http://haeji.mawani.kro.kr:9999/QR/image/" + qr.getMqiUri();
        this.menuNutritionFile = nutrition.getMnImgFile();
        this.menuNutritionUri = "http://haeji.mawani.kro.kr:9999/image/nutri/" + nutrition.getMnUri();
    }
}
