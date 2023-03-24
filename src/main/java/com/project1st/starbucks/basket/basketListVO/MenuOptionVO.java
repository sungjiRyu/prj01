package com.project1st.starbucks.basket.basketListVO;

import com.project1st.starbucks.menu.entity.MenuOptionInfoEntity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MenuOptionVO {
    private Long seq;
    private Long name;
    private Long cost;
    private Long optionCateSeq;
    public static MenuOptionVO fromEntity(MenuOptionInfoEntity menuOptionInfoEntity) {
        return MenuOptionVO.builder()
        .seq(menuOptionInfoEntity.getMoiSeq()).build();
    }
}
