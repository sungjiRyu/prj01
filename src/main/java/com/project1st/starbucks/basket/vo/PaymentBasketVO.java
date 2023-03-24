package com.project1st.starbucks.basket.vo;

import lombok.Data;

@Data
public class PaymentBasketVO {
    private Long miSeq;
    private String sbRequest;
    private String sbReceive;
    private Long sbPayment;
    private Long couponUse;
    private Long couponNumber;
}
