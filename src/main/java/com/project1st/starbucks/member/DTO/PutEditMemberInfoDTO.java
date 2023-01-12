package com.project1st.starbucks.member.DTO;


import com.project1st.starbucks.admin.entity.MemberEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 회원 수정 정보를 받아올 DTO 생성
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PutEditMemberInfoDTO {
    private String pwd;
    private String checkPwd;    
    private String nickName;
    private String name;
    private String phoneNum;
    private String adress;
    private String detailAdress;

    public static PutEditMemberInfoDTO fromEntity(MemberEntity memberInfoEntity){
        return PutEditMemberInfoDTO.builder()
        .pwd(memberInfoEntity.getMiPwd())
        .checkPwd(memberInfoEntity.getMiPwd())
        .nickName(memberInfoEntity.getMiNickname())
        .name(memberInfoEntity.getMiName())
        .phoneNum(memberInfoEntity.getMiPhoneNum())
        .adress(memberInfoEntity.getMiAddress())
        .detailAdress(memberInfoEntity.getMiDetailAddress())
        .build();
    }

}
