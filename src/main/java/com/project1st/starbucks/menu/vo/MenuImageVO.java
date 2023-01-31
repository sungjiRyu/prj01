package com.project1st.starbucks.menu.vo;

import com.project1st.starbucks.admin.entity.MenuImageEntity;

import lombok.Data;

@Data
public class MenuImageVO {
    private Long menuNo;
    private String menuName;
    private Integer menuCost;
    private Integer menuStatus;
    private String menuExplain;
    private Long menuCategorySeq;
    private String menuFile;
    private String menuUri;

    public MenuImageVO() {}
    public MenuImageVO(MenuImageEntity image) {
        this.menuNo = image.getMiiNumber().getMbiSeq();
        this.menuName = image.getMiiNumber().getMbiName();
        this.menuCost = image.getMiiNumber().getMbiCost();
        this.menuStatus = image.getMiiNumber().getMbiStatus();
        this.menuExplain = image.getMiiNumber().getMbiExplain();
        this.menuCategorySeq = image.getMiiNumber().getMbiPcSeq();
        this.menuFile = image.getMiiImgFile();
        this.menuUri = "http://haeji.mawani.kro.kr:9999/image/menu/" + image.getMiiUri();
    }
}
