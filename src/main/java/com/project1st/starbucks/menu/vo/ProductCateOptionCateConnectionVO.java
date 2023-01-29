package com.project1st.starbucks.menu.vo;

import com.project1st.starbucks.menu.entity.MenuOptionCategoryEntity;
import com.project1st.starbucks.menu.entity.ProductCateOptionCateConnectionEntity;
import com.project1st.starbucks.menu.entity.ProductCategoryEntity;

import lombok.Data;

@Data
public class ProductCateOptionCateConnectionVO {
    private Long pocSeq;
    private ProductCategoryEntity productCategory;
    private MenuOptionCategoryEntity menuOpionCategory;

    public ProductCateOptionCateConnectionVO(ProductCateOptionCateConnectionEntity entity) {
        this.pocSeq = entity.getPocSeq();
        this.productCategory = entity.getProductCategory();
        this.menuOpionCategory = entity.getMenuOpionCategory();
    }
}
