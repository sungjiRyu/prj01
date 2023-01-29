package com.project1st.starbucks.store.vo;



import java.time.LocalTime;

import com.project1st.starbucks.store.entity.StoreBasicInfoEntity;

import lombok.Data;

@Data
public class StoreInfoVO {
    private Long storeNo;
    private String branch;
    private String addressBasic;
    private String addressDetail;
    private LocalTime openTime;
    private LocalTime closeTime;
    private String closeDays;
    private Integer minOrderPrice;
    private String agent;
    private String businessAddress;
    private String phoneNo;
    private String minDeliveryTime;
    private String maxDeliveryTime;

    public StoreInfoVO(StoreBasicInfoEntity entity) {
        this.storeNo = entity.getSbiSeq();
        this.branch = entity.getSbiBranchName();
        this.addressBasic = entity.getSbiAddressBasic();
        this.addressDetail = entity.getSbiAddressDetail();
        this.openTime = entity.getSbiOpenTime();
        this.closeTime = entity.getSbiCloseTime();
        this.closeDays = entity.getSbiCloseDay();
        this.minOrderPrice = entity.getSbiMinOrder();
        this.agent = entity.getSbiCeoName();
        this.businessAddress = entity.getSbiBuisnessAddress();
        this.phoneNo = entity.getSbiPhone();
        this.minDeliveryTime = entity.getSbiMinDeliveryTime();
        this.maxDeliveryTime = entity.getSbiMaxDeliveryTime();
    }
}
