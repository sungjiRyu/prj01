package com.starbucks.final_project01;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.starbucks.final_project01.entity.MemberEntity;
import com.starbucks.final_project01.repository.MemberInfoRepository;
import com.starbucks.final_project01.service.MemberService;
import com.starbucks.final_project01.util.AESAlgorithm;

@SpringBootTest
class FinalProject01ApplicationTests {
	@Autowired MemberInfoRepository mRepo;
	@Autowired MemberService mService;
	// entity 는 autowired 대상이 아니다
	String phone = "010-1111-1112";
	String pwd = "123456";
	// 중복가입 방지 메소드(휴대폰 번호로 판별)
 	@Test
	void duplicatedMember() {
		if(mRepo.countBymiPhoneNum(phone) == 1){
			System.out.println("이미 등록된 회원입니다.");
		}
		else System.out.println("회원가입이 완료되었습니다.");
	}

	// ID중복체크 확인
	
	@Test
	void duplicatedId(){
		String id = "asdf";
		Map<String, Object> resultMap = mService.checkIdDuplicat(id);
		System.out.println(resultMap);
	}
	
	// 회원가입 테스트코드
	@Test
	void insertMember() {
		MemberEntity data = new MemberEntity();
		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        // 휴대폰 번호 판별해서 중복가입 방지 
        if(mRepo.countBymiPhoneNum(data.getMiPhoneNum()) == 1){
            resultMap.put("status",false);
            resultMap.put("message", "이미 등록된 사용자 입니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        // 중복되는 휴대폰 번호가 없다면
        else{
        // 비밀번호 암호화
            try{
                String encPwd = AESAlgorithm.Encrypt(data.getMiPwd());
                data.setMiPwd(encPwd);
            } 
            catch(Exception e) {e.printStackTrace();}
            // 
            // 입력받은 데이터 DB에 저장(MemberInfo)
            mRepo.save(data);                        
            resultMap.put("status", true);
            resultMap.put("message", "회원이 등록되었습니다.");
            resultMap.put("code", HttpStatus.CREATED);
        }
	}

	// @Test
	// void showMyinfo(){
	// 	// 로그인한 회원 정보 조회
    // Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    // MemberEntity memberInfo = null;
    //  memberInfo = mRepo.findByMiId(loginUser.getMiId());
    // if(memberInfo != null){
    //         resultMap.put("status", true);
    //         resultMap.put("message", "현재 로그인한 사용자 정보");
    //         resultMap.put("code", HttpStatus.ACCEPTED);
    //         resultMap.put("memberInfo", memberInfo);
    //         return resultMap;
    //     }
    // else{
    //     resultMap.put("status", false);
    //     resultMap.put("message", "오류발생");
    //     resultMap.put("code", HttpStatus.BAD_REQUEST);
    //     return resultMap;
    //     }
    // }

	@Test
	void dupcheck(){
		String content = "user005";
		Integer dupcheck = mRepo.countBymiId(content);
		System.out.println(dupcheck);
	}
	}

	// 로그인 기능
	// 	@Test
	// 	void memberLogin(){
	// 		MemberEntity data = new MemberEntity();
	// 		Map<String, Object> loginMember(MemberEntity data) {
	// 		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
	// 		MemberEntity loginUser = null;
	// 		try {
	// 			loginUser = mRepo.findByMiIdAndMiPwd(data.getMiId(), AESAlgorithm.Encrypt(data.getMiPwd()));
	// 		} catch(Exception e) {e.printStackTrace();}
	// 		if(loginUser == null) {
	// 			resultMap.put("status", false);
	// 			resultMap.put("message", "아이디 혹은 비밀번호 오류입니다.");
	// 			resultMap.put("code", HttpStatus.BAD_REQUEST);
	// 		} else {
	// 			resultMap.put("status", true);
	// 			resultMap.put("message", "로그인 되었습니다");
	// 			resultMap.put("code", HttpStatus.ACCEPTED);
	// 			resultMap.put("loginUser", loginUser);
	// 		}
	// 	}
	// }
