package com.project1st.starbucks.menu.vo;

import java.util.List;

import lombok.Data;

@Data
public class MenuOptionListVO {
    private String OptionCategoryName;
    private List<MenuOptionVO> Option;
}
