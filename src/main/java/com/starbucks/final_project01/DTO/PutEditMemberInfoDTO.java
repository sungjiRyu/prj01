package com.starbucks.final_project01.DTO;

<<<<<<< HEAD
import com.starbucks.final_project01.entity.MemberEntity;

import io.micrometer.common.lang.Nullable;
=======
import com.starbucks.final_project01.entity.MemberInfoEntity;

>>>>>>> 34c9e0df497c5a98f09199fd1e52287aa75e61ed
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

<<<<<<< HEAD
    public static PutEditMemberInfoDTO fromEntity(MemberEntity memberInfoEntity){
=======
    public static PutEditMemberInfoDTO fromEntity(MemberInfoEntity memberInfoEntity){
>>>>>>> 34c9e0df497c5a98f09199fd1e52287aa75e61ed
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
<<<<<<< HEAD
=======

>>>>>>> 34c9e0df497c5a98f09199fd1e52287aa75e61ed
}

