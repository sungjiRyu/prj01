package com.project1st.starbucks.membershipcard.vo;

import com.project1st.starbucks.membershipcard.entity.MembershipCardEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MembershipCardChargeVO {
    private Integer money;
    
    public MembershipCardChargeVO cardEntity (MembershipCardEntity membershipCard) {
        return MembershipCardChargeVO.builder()
        .money(membershipCard.getCardMoney())
        .build();
    }

}
