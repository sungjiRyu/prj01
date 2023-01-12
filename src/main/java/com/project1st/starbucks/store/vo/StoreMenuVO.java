package com.project1st.starbucks.store.vo;

import java.util.List;

import com.project1st.starbucks.menu.vo.MenuInfoVO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StoreMenuVO {
    private StoreInfoVO store;
    private List<MenuInfoVO> menus;
}