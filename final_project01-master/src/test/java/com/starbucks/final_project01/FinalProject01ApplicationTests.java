package com.starbucks.final_project01;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.starbucks.final_project01.entity.MemberInfoEntity;
import com.starbucks.final_project01.repository.MemberInfoRepository;
import com.starbucks.final_project01.util.AESAlgorithm;

@SpringBootTest
class FinalProject01ApplicationTests {
	@Autowired MemberInfoRepository mRepo;
	// entity 는 autowired 대상이 아니다
	String phone = "010-1111-1112";
	String pwd = "123456";
	// 중복가입 방지 메소드(휴대폰 번호로 판별)
 	@Test
	void duplicatedMember() {
		if(mRepo.countByphoneNum(phone) == 1){
			System.out.println("이미 등록된 회원입니다.");
		}
		else System.out.println("회원가입이 완료되었습니다.");
	}
}

