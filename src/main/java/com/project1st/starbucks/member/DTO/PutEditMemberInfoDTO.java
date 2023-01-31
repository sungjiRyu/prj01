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
    private String miPwd;
    private String miCheckPwd;    
    private String miNickName;
    private String miName;
    private String miPhoneNum;
    private String miAdress;
    private String miDetailAdress;

    public static PutEditMemberInfoDTO fromEntity(MemberEntity memberInfoEntity){
        return PutEditMemberInfoDTO.builder()
        .miPwd(memberInfoEntity.getMiPwd())
        .miCheckPwd(memberInfoEntity.getMiPwd())
        .miNickName(memberInfoEntity.getMiNickname())
        .miName(memberInfoEntity.getMiName())
        .miPhoneNum(memberInfoEntity.getMiPhoneNum())
        .miAdress(memberInfoEntity.getMiAddress())
        .miDetailAdress(memberInfoEntity.getMiDetailAddress())
        .build();
    }

}

