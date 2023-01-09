package com.starbucks.final_project01.DTO;

import java.util.Date;

import com.starbucks.final_project01.entity.MemberInfoEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Builder
@Getter
public class MemberInfoDTO {
    
    private Long    miSeq;
    private String  miId;
    private String  miPwd;
    private String  miName;
    private String  miNickname;
    private Date    miBirth;
    private Integer miGen;
    private Integer miStatus;
    private String  miPhoneNum;
    private Date    miRegDate;
    private Integer miGroup;
    private String  miBusinessNum;
    private String  miAddress;
    private Date    miLastLogin;
    private String  miDetailAddress;

    // 아이디 중복체크(DB에 저장된 아이디 조회)
    public static MemberInfoDTO checkDuplicatedId(MemberInfoEntity memberInfo) {
        return MemberInfoDTO.builder()
        .miId(memberInfo.getMiId())
        .build();
    }

    // 전화번호 중복체크(DB저장된 전화번호 조회)
    public static MemberInfoDTO checkDuplicatedPhoneNum(MemberInfoEntity memberInfo){
        return MemberInfoDTO.builder()
        .miPhoneNum(memberInfo.getMiPhoneNum())
        .build();
    }

    // 회원가입 정보 저장
    public static MemberInfoDTO regMemberInfo(MemberInfoEntity memberInfo){
        return MemberInfoDTO.builder()
        .miSeq(memberInfo.getMiSeq())
        .miId(memberInfo.getMiId())
        .miPwd(memberInfo.getMiPwd())
        .miName(memberInfo.getMiName())
        .miNickname(memberInfo.getMiNickname())
        .miBirth(memberInfo.getMiBirth())
        .miGen(memberInfo.getMiGen())
        // .miStatus(memberInfo.getMiStatus())              // 회원 상태값(1. 기본 2. 정지 3.탈퇴)
        .miPhoneNum(memberInfo.getMiPhoneNum())
        // .miRegDate(memberInfo.getMiRegDate())            // 회원가입일
        .miGroup(memberInfo.getMiGroup())
        .miBusinessNum(memberInfo.getMiBusinessNum())
        .miAddress(memberInfo.getMiAddress())
        // .miLastLogin(memberInfo.getMiLastLogin())        // 마지막 로그인
        .miDetailAddress(memberInfo.getMiDetailAddress())
        .build();
    } 

    // 로그인 DTO
    // MemberInfoEntity 중 miId(아이디) 와 miPwd(비밀번호) 만 사용 하는 DTO 생성
    public static MemberInfoDTO memberLoginDTO(MemberInfoEntity memberInfoEntity){
        return MemberInfoDTO.builder()
        .miId(memberInfoEntity.getMiId())
        .miPwd(memberInfoEntity.getMiPwd())
        .build();
    }

    
    
}

