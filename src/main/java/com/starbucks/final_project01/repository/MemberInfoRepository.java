package com.starbucks.final_project01.repository;

import org.springframework.data.jpa.repository.JpaRepository;

<<<<<<< HEAD
import com.starbucks.final_project01.entity.MemberEntity;

public interface MemberInfoRepository extends JpaRepository <MemberEntity, Long>  {
=======
import com.starbucks.final_project01.entity.MemberInfoEntity;

public interface MemberInfoRepository extends JpaRepository <MemberInfoEntity, Long>  {
>>>>>>> 34c9e0df497c5a98f09199fd1e52287aa75e61ed
    // 아이디 중복 검사
    Integer countBymiId(String miId);
    // 닉네임 중복 검사
    Integer countBymiNickname(String miNickName);
    // 휴대폰 번호 중복 검사
    Integer countBymiPhoneNum(String miPhoneNum);
<<<<<<< HEAD
    // 사업자번호 중복검사
    Integer countBymiBusinessNum(String miBusinessNum);
    // Integer countByMiBusinessNum
    



    MemberEntity findByMiIdAndMiPwd(String miId, String miPwd);
    // MemberEntity findByUserIdAndUserPwd(String id, String pwd);

    // 현재 로그인한 사용자 아이디와 일치하는 사용자 정보 출력 ( 로그인 사용자 정보 조회 )
    MemberEntity findByMiId(String miId);
    
    // 로그인한 회원 miSeq에 해당하는 정보 출력 
    MemberEntity findByMiSeq(Long miSeq);

=======

    MemberInfoEntity findByMiIdAndMiPwd(String miId, String miPwd);
    // MemberInfoEntity findByUserIdAndUserPwd(String id, String pwd);

    // 현재 로그인한 사용자 아이디와 일치하는 사용자 정보 출력 ( 로그인 사용자 정보 조회 )
    MemberInfoEntity findByMiId(String miId);
    
    // 로그인한 회원 miSeq에 해당하는 정보 출력 
    MemberInfoEntity findByMiSeq(Long miSeq);
>>>>>>> 34c9e0df497c5a98f09199fd1e52287aa75e61ed
}
