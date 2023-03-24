package com.project1st.starbucks.member.DTO;

import java.time.LocalDate;
import java.util.Date;

import com.project1st.starbucks.admin.entity.MemberEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetLoginUserInfoDTO{

    private Long miSeq;
    private String miId;
    private String miName;
    private String miNickname;
    private LocalDate   miBirth;
    private Integer miGen;
    private String miPhoneNum;
    private Integer miStatus;
    private LocalDate miRegDate;
    private Integer miGroup;
    private String miBusinessNum;
    private String miAddress;
    private String miDetailAddress;
    private LocalDate miLastLogin;
    private Long miSbiSeq;

public static GetLoginUserInfoDTO getLoginUserInfoDTO (MemberEntity loginUserInfo){
    return GetLoginUserInfoDTO.builder()
    .miSeq(loginUserInfo.getMiSeq())
    .miId(loginUserInfo.getMiId())
    .miName(loginUserInfo.getMiName())
    .miNickname(loginUserInfo.getMiNickname())
    .miBirth(loginUserInfo.getMiBirth())
    .miGen(loginUserInfo.getMiGen())
    .miPhoneNum(loginUserInfo.getMiPhoneNum())
    .miStatus(loginUserInfo.getMiStatus())
    .miRegDate(loginUserInfo.getMiRegDate())
    .miGroup(loginUserInfo.getMiGroup())
    .miBusinessNum(loginUserInfo.getMiBusinessNum())
    .miAddress(loginUserInfo.getMiAddress())
    .miDetailAddress(loginUserInfo.getMiDetailAddress())
    .miLastLogin(loginUserInfo.getMiLastLogin())
    .miSbiSeq(loginUserInfo.getMiSbiSeq())
    .build();
}
}

