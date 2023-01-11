package com.starbucks.final_project01.DTO;


import java.time.LocalDate;

import com.starbucks.final_project01.entity.MemberEntity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

// 마이페이지 회원정보 확인하기 
public class GetShowMemberInfoDTO {
    private String id;
    private String name;
    private String nickName;
    private LocalDate Birth;
    private Integer gen;
    private String PhoneNum;
    private Integer Group;
    private String BusinessNum;
    private String Address;
    private String DetailAddress;

    public static GetShowMemberInfoDTO getShowMemberInfoDTO (MemberEntity memberInfo){
        return GetShowMemberInfoDTO.builder()
        .id(memberInfo.getMiId())
        .name(memberInfo.getMiName())
        .nickName(memberInfo.getMiNickname())
        .Birth(memberInfo.getMiBirth())
        .gen(memberInfo.getMiGen())
        .PhoneNum(memberInfo.getMiPhoneNum())
        .Group(memberInfo.getMiGroup())
        .BusinessNum(memberInfo.getMiBusinessNum())
        .Address(memberInfo.getMiAddress())
        .DetailAddress(memberInfo.getMiDetailAddress())
        .build();

        
    }


}
