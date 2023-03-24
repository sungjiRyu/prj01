package com.project1st.starbucks.menu.vo;

import java.util.List;


import lombok.Data;

@Data
public class MenuDetailOptionListVO {
    private Long sbSmcSeq;
    private MenuDetailVO detail;
    private List<MenuOptionListVO> options;
}
