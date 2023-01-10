package com.starbucks.final_project01.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.starbucks.final_project01.DTO.GetShowMemberInfoDTO;
import com.starbucks.final_project01.DTO.LoginDTO;
import com.starbucks.final_project01.DTO.MemberInfoDTO;
import com.starbucks.final_project01.DTO.PostLoginDTO;
import com.starbucks.final_project01.DTO.PutEditMemberInfoDTO;
import com.starbucks.final_project01.entity.MemberInfoEntity;
import com.starbucks.final_project01.repository.MemberInfoRepository;
import com.starbucks.final_project01.util.AESAlgorithm;

import jakarta.servlet.http.HttpSession;


@Service
public class MemberService {
    // MemberInfoEntity loginUser = new MemberInfoEntity();
    @Autowired MemberInfoRepository mRepo;
    // 일반회원가입 메소드
    public Map<String, Object> joinNomalMember(MemberInfoEntity data){
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
        return resultMap;
    }
    // 아이디 중복체크
    public Map<String, Object> checkIdDuplicat (String id){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if(mRepo.countBymiId(id) == 1){
            resultMap.put("status", false);
            resultMap.put("message", "중복된 아이디");
            resultMap.put("code", HttpStatus.CONFLICT);
        }
        else {
            resultMap.put("status", true);
            resultMap.put("message", "사용가능한 아이디");
            resultMap.put("code", HttpStatus.OK);
        }
        return resultMap;
    }

    // 점주회원가입 메소드
    public Map<String, Object> joinOwnerMember(MemberInfoEntity data){
        MemberInfoEntity  miEntity = new MemberInfoEntity(); 
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
         // mi_group 2로 입력 (1.일반회원 2.점주회원)
        //  miEntity.setMiGroup(data.getMiGroup());
        data.setMiGroup(2);
         // 입력받은 데이터 DB에 저장(MemberInfo)
         mRepo.save(data);                        
         resultMap.put("status", true);
         resultMap.put("message", "회원이 등록되었습니다.");
         resultMap.put("code", HttpStatus.CREATED);
        }
        return resultMap;
 }

   



    // 로그인 메소드  회원 상태값(1. 기본 2. 정지 3.탈퇴)
public Map<String, Object> loginMember(PostLoginDTO data){
    Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        MemberInfoEntity loginUser = null;
        try {
            loginUser = mRepo.findByMiIdAndMiPwd(data.getId(), AESAlgorithm.Encrypt(data.getPwd()));
        } catch(Exception e) {e.printStackTrace();}
        // 회원정보가 없을때(탈퇴회원도 동일) 
        if(loginUser == null || loginUser.getMiStatus() == 3) {
            resultMap.put("status", false);
            resultMap.put("message", "아이디 혹은 비밀번호 오류입니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        } 
        // 회원 상태값 2일때 (정지된 회원)
        else if(loginUser.getMiStatus() == 2){
            resultMap.put("status", false);
            resultMap.put("message", "정지된 회원입니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        // 회원 상태값 1일때(정상로그인)
        else {
            resultMap.put("status", true);
            resultMap.put("message", "로그인 되었습니다");
            resultMap.put("code", HttpStatus.ACCEPTED);
            resultMap.put("loginUser", loginUser);
        }
        return resultMap;
    }

// 로그인한 회원 정보 조회
public Map<String, Object> showLoginMemberInfo(HttpSession session){
    Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    MemberInfoEntity memberInfo = (MemberInfoEntity)session.getAttribute("loginUser");
    if(memberInfo != null){
            memberInfo = mRepo.findByMiId(memberInfo.getMiId());
            resultMap.put("status", true);
            resultMap.put("message", "현재 로그인한 사용자 정보");
            resultMap.put("code", HttpStatus.ACCEPTED);
            resultMap.put("memberInfo", memberInfo);
            return resultMap;
        }
    else{
        resultMap.put("status", false);
        resultMap.put("message", "로그인 정보가 잘못되었습니다.");
        resultMap.put("code", HttpStatus.BAD_REQUEST);
        return resultMap;
        }
    }

// 회원정보 수정
    // editMemberInfo 통해서 수정할 회원정보 받아옴
    // session 에담긴 회원seq 통해서 수정할 회원정보 찾기
    
public Map<String, Object> editMemberInfo(HttpSession session, PutEditMemberInfoDTO editMemberInfo){
    Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    // session의 로그인 정보를 memberInfo에 담아둠
    MemberInfoEntity memberInfo = (MemberInfoEntity)session.getAttribute("loginUser");
    // session의 miSeq로 로그인한 회원정보 끌어와서 
    // editMemberInfo로 받은 입력값만 수정
    // memberInfo = mRepo.findByMiSeq(memberInfo.getMiSeq()); // memberInfo 객체에 로그인한 회원정보 넣어주기
    // 로그인한 회원정보가 있다면(= session에 데이터가 있다면) 회원수정 실행
    if(memberInfo != null){
        try{String encPwd = AESAlgorithm.Encrypt(editMemberInfo.getPwd());
            String checkEncPwd = AESAlgorithm.Encrypt(editMemberInfo.getCheckPwd());
            memberInfo.setMiPwd(encPwd);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        memberInfo.setMiName(editMemberInfo.getName());
        memberInfo.setMiNickname(editMemberInfo.getNickName());
        memberInfo.setMiPhoneNum(editMemberInfo.getPhoneNum());
        memberInfo.setMiAddress(editMemberInfo.getAdress());
        memberInfo.setMiDetailAddress(editMemberInfo.getDetailAdress());
        mRepo.save(memberInfo);
        resultMap.put("status", true);
        resultMap.put("message", "회원정보가 수정되었습니다.");
        resultMap.put("code", HttpStatus.OK);
    }
    else if(memberInfo == null){
        resultMap.put("status", false);
        resultMap.put("message", "로그인을 해주세요.");
        resultMap.put("code", HttpStatus.BAD_REQUEST);
    }
    return resultMap;
}
}  
    
    

