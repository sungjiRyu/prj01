package com.starbucks.final_project01.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.starbucks.final_project01.entity.MemberInfoEntity;

public interface MemberInfoRepository extends JpaRepository <MemberInfoEntity, Long>  {
    // 아이디 중복 검사
    public Integer countBymiId(String miId);
    // 닉네임 중복 검사
    public Integer countBymiNickname(String miNickname);
    // 휴대폰 번호 중복 검사
    public Integer countByphoneNum(String phoneNum);
    // 아이디와 비밀번호 조회
    public MemberInfoEntity findByMiIdAndMiPwd(String miId, String miPwd);

}  
