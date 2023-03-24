package com.project1st.starbucks.membershipcard.vo;

import com.project1st.starbucks.membershipcard.entity.MembershipCardEntity;
import com.project1st.starbucks.membershipcard.entity.MembershipCardImageEntity;
import com.project1st.starbucks.membershipcard.entity.MembershipCardQREntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MembershipCardDetailVO {
    private Long cardSeq;
    private String cardName;
    private Integer money;
    private Long cardMemberSeq;
    private Long cardImage;
    private String cardImageFile;
    private String cardImageUri;
    private String cardQRFile;
    private String cardQRUri;

    public MembershipCardDetailVO(MembershipCardEntity card, MembershipCardImageEntity cardImage, MembershipCardQREntity cardQr) {
        this.cardSeq = card.getCardSeq();
        this.cardName = card.getCardName();
        this.money = card.getCardMoney();
        this.cardMemberSeq = card.getCardMiSeq();
        this.cardImage = card.getCardImage();
        this.cardImageFile = cardImage.getCardimageFile();
        this.cardImageUri = "http://haeji.mawani.kro.kr:9999/image/membership/" + cardImage.getCardimageUri();
        this.cardQRFile = cardQr.getCardqrFile();
        this.cardQRUri = "http://haeji.mawani.kro.kr:9999/image/cardqr/" + cardQr.getCardqrUri();
    }

}