package com.project1st.starbucks.store.vo;

import java.util.List;

import com.project1st.starbucks.menu.vo.MenuStockVO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class storeMenuStockVO {
    private StoreInfoVO store;
    private List<MenuStockVO> menu;
}
