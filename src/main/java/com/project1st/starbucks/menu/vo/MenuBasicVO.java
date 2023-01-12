package com.project1st.starbucks.menu.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuBasicVO {
private String mbiName;
private Integer mbiCost;
private Integer mbiStatus;
private String mbiExplain;
private Long mbiPcSeq;
}
