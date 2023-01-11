package com.starbucks.final_project01.DTO;


<<<<<<< HEAD
import java.time.LocalDate;
import java.util.Date;

import com.starbucks.final_project01.entity.MemberEntity;
=======
import java.util.Date;

import com.starbucks.final_project01.entity.MemberInfoEntity;
>>>>>>> 34c9e0df497c5a98f09199fd1e52287aa75e61ed

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
<<<<<<< HEAD
    private LocalDate Birth;
=======
    private Date Birth;
>>>>>>> 34c9e0df497c5a98f09199fd1e52287aa75e61ed
    private Integer gen;
    private String PhoneNum;
    private Integer Group;
    private String BusinessNum;
    private String Address;
    private String DetailAddress;


    
<<<<<<< HEAD
    public static GetShowMemberInfoDTO getShowMemberInfoDTO (MemberEntity memberInfo){
=======
    public static GetShowMemberInfoDTO getShowMemberInfoDTO (MemberInfoEntity memberInfo){
>>>>>>> 34c9e0df497c5a98f09199fd1e52287aa75e61ed
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
