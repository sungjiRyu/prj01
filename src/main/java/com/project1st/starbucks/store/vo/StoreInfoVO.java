package com.project1st.starbucks.store.vo;

import com.project1st.starbucks.admin.entity.StoreImageEntity;
import com.project1st.starbucks.store.entity.StoreBasicInfoEntity;

import lombok.Data;

@Data
public class StoreInfoVO {
    private Long storeNo;
    private String branchName;
    private String addressBasic;
    private String addressDetail;
    private String openTime;
    private String closeTime;
    private String closeDays;
    private Integer minOrderPrice;
    private String owner;
    private String businessAddress;
    private String phoneNo;
    private String minDeliveryTime;
    private String maxDeliveryTime;
    private String storeImageFile;
    private String storeImageUri;

    public StoreInfoVO(){}
    public StoreInfoVO(StoreBasicInfoEntity entity, StoreImageEntity imageEntity) {
        this.storeNo = entity.getSbiSeq();
        this.branchName = entity.getSbiBranchName();
        this.addressBasic = entity.getSbiAddressBasic();
        this.addressDetail = entity.getSbiAddressDetail();
        this.openTime = entity.getSbiOpenTime();
        this.closeTime = entity.getSbiCloseTime();
        this.closeDays = entity.getSbiCloseDay();
        this.minOrderPrice = entity.getSbiMinOrder();
        this.owner = entity.getSbiCeoName();
        this.businessAddress = entity.getSbiBuisnessAddress();
        this.phoneNo = entity.getSbiPhone();
        this.minDeliveryTime = entity.getSbiMinDeliveryTime();
        this.maxDeliveryTime = entity.getSbiMaxDeliveryTime();
        this.storeImageFile = imageEntity.getSiImgFile();
        this.storeImageUri = "http://haeji.mawani.kro.kr:9999/image/store/" + imageEntity.getSiUri();
    }
}
