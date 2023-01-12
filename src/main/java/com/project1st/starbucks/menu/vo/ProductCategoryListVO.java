package com.project1st.starbucks.menu.vo;

import java.util.List;

import lombok.Data;

@Data
public class ProductCategoryListVO {
    private String categoryName;
    private List<String> childCategoryName;
}
