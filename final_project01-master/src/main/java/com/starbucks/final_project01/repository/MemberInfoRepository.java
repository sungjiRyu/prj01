package com.starbucks.final_project01.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.starbucks.final_project01.entity.MemberInfoEntity;

public interface MemberInfoRepository extends JpaRepository <MemberInfoEntity, Long>  {
    // 아이디 중복 검사
    Integer countBymiId(String miId);
    // 닉네임 중복 검사
    Integer countBymiNickname(String miNickName);
    // 휴대폰 번호 중복 검사
    Integer countBymiPhoneNum(String miPhoneNum);

    MemberInfoEntity findByMiIdAndMiPwd(String miId, String miPwd);
    // MemberInfoEntity findByUserIdAndUserPwd(String id, String pwd);
}
