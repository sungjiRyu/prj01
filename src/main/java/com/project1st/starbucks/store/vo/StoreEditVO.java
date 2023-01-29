package com.project1st.starbucks.store.vo;

import java.sql.Time;
import java.time.LocalTime;

import com.project1st.starbucks.store.entity.StoreBasicInfoEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreEditVO {
    private LocalTime storeOpenTime;
    private LocalTime storeCloseTime;
    private String storeCloseDay;
    private Integer storeMinOrder;
    private String storePhone;
    private String storeMinDeliveryTime;
    private String storeMaxDeliveryTime;


    public static StoreEditVO storeEditEntity(StoreBasicInfoEntity storeBasicInfoEntity) {
        return StoreEditVO.builder()
        .storeOpenTime(storeBasicInfoEntity.getSbiOpenTime())
        .storeCloseTime(storeBasicInfoEntity.getSbiCloseTime())
        .storeCloseDay(storeBasicInfoEntity.getSbiCloseDay())
        .storeMinOrder(storeBasicInfoEntity.getSbiMinOrder())
        .storePhone(storeBasicInfoEntity.getSbiPhone())
        .storeMinDeliveryTime(storeBasicInfoEntity.getSbiMinDeliveryTime())
        .storeMaxDeliveryTime(storeBasicInfoEntity.getSbiMaxDeliveryTime())
        .build();
    }
}
