package com.project1st.starbucks.menu.vo;

import com.project1st.starbucks.admin.entity.MenuImageEntity;
import com.project1st.starbucks.menu.entity.MenuBasicInfoEntity;
import com.project1st.starbucks.menu.entity.MenuQrEntity;

import lombok.Data;

@Data
public class MenuDetailVO {
    private Long menuSeq;
    private String menuName;
    private Integer menuCost;
    private Integer menuStatus;
    private String menuExplain;
    private Integer menuCategorySeq;
    private String menuFile;
    private String menuUri;
    private String menuQrFile;
    private String menuQrUri;

    public MenuDetailVO(MenuImageEntity image, MenuQrEntity qr) {
        this.menuSeq = image.getMiiNumber().getMbiSeq();
        this.menuName = image.getMiiNumber().getMbiName();
        this.menuCost = image.getMiiNumber().getMbiCost();
        this.menuStatus = image.getMiiNumber().getMbiStatus();
        this.menuExplain = image.getMiiNumber().getMbiExplain();
        this.menuCategorySeq = image.getMiiNumber().getMbiPcSeq();
        this.menuFile = image.getMiiImgFile();
        this.menuUri = image.getMiiUri();
        this.menuQrFile = qr.getMqiImageFile();
        this.menuQrUri = qr.getMqiUri();
    }
}
