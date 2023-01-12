package com.project1st.starbucks.store.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreMenuDeleteVO {
    private Long store;
    private Long menu;

    public void copyData(StoreMenuDeleteVO src) {
        if(src.getStore() != null)   this.store = src.getStore();
        if(src.getMenu() != null)   this.menu = src.getMenu();
        // this.store = data.getStore().getSbiSeq();
        // this.menu = data.getMenu().getMbiSeq();
    }
}
