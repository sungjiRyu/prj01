package com.project1st.starbucks.menu.vo;

import java.util.List;

import com.project1st.starbucks.menu.entity.MenuBasicInfoEntity;

import lombok.Data;

@Data
public class MenuDetailOptionListVO {
    private MenuDetailVO detail;
    private List<MenuOptionListVO> options;

}
