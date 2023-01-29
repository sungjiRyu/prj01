package com.project1st.starbucks.menu.vo;

import com.project1st.starbucks.menu.entity.MenuOptionInfoEntity;

import lombok.Data;

@Data
public class MenuOptionVO {
    private String menuOptionName;
    private Integer menuOptionCost;
    
    public MenuOptionVO( MenuOptionInfoEntity option) {
        this.menuOptionName = option.getMoiName();
        this.menuOptionCost = option.getMoiCost();
    }
}
