package com.project1st.starbucks.store.vo;

import java.util.List;

import com.project1st.starbucks.menu.vo.MenuStockDetailVO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StoreMenuDetailVO {
    private StoreInfoVO store;
    private List<MenuStockDetailVO> menu;
}