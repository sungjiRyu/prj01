package com.project1st.starbucks.admin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project1st.starbucks.admin.entity.MemberEntity;



public interface MemberInfoRepository extends JpaRepository <MemberEntity, Long>  {
    // 아이디 중복 검사
    Integer countBymiId(String miId);
    // 닉네임 중복 검사
    Integer countBymiNickname(String miNickName);
    // 휴대폰 번호 중복 검사
    Integer countBymiPhoneNum(String miPhoneNum);
    // 사업자번호 중복검사
    Integer countBymiBusinessNum(String miBusinessNum);
    // Integer countByMiBusinessNum
   
    MemberEntity findByMiIdAndMiPwd(String miId, String miPwd);
    // MemberEntity findByUserIdAndUserPwd(String id, String pwd);

    // 현재 로그인한 사용자 아이디와 일치하는 사용자 정보 출력 ( 로그인 사용자 정보 조회 )
    MemberEntity findByMiId(String miId);
    
    // 로그인한 회원 miSeq에 해당하는 정보 출력 
    MemberEntity findByMiSeq(Long miSeq);

     // 이름과 전화번호 (아이디 찾기 본인인증 메소드에 사용)
   MemberEntity findByMiNameAndMiPhoneNum(String miName, String miPhoneNum );

    // 전화번호로 회원정보 불러오기(아이디찾기 메소드에 사용)
    MemberEntity findByMiPhoneNum(String miPhoneNum);

    // 이름과 아이디 (비밀번호 찾기 본인인증 메소드에 사용)
    MemberEntity findByMiNameAndMiId(String miName, String miId );

    // 이름으로 회원정보 찾기
    MemberEntity findByMiName(String MiNAme);
}
