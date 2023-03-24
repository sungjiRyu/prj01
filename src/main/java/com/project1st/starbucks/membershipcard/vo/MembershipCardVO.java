package com.project1st.starbucks.membershipcard.vo;

import com.project1st.starbucks.membershipcard.entity.MembershipCardEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MembershipCardVO {
    private Long cardNo;
    private String cardName;
    private Integer money;
    private Long cardUserNo;


    public MembershipCardVO (MembershipCardEntity cardEntity) {
        this.cardNo = cardEntity.getCardSeq();
        this.cardName = cardEntity.getCardName();
        this.money = cardEntity.getCardMoney();
        this.cardUserNo = cardEntity.getCardMiSeq();
    }
}

