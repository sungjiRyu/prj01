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
<<<<<<< HEAD
import com.starbucks.final_project01.entity.MemberEntity;
=======
import com.starbucks.final_project01.entity.MemberInfoEntity;
>>>>>>> 34c9e0df497c5a98f09199fd1e52287aa75e61ed
import com.starbucks.final_project01.repository.MemberInfoRepository;
import com.starbucks.final_project01.util.AESAlgorithm;

import jakarta.servlet.http.HttpSession;


@Service
public class MemberService {
<<<<<<< HEAD
    // MemberEntity loginUser = new MemberEntity();
    @Autowired MemberInfoRepository mRepo;
    // 일반회원가입 메소드
    public Map<String, Object> joinNomalMember(MemberEntity data){
=======
    // MemberInfoEntity loginUser = new MemberInfoEntity();
    @Autowired MemberInfoRepository mRepo;
    // 일반회원가입 메소드
    public Map<String, Object> joinNomalMember(MemberInfoEntity data){
>>>>>>> 34c9e0df497c5a98f09199fd1e52287aa75e61ed
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
<<<<<<< HEAD
    public Map<String, Object> joinOwnerMember(MemberEntity data){
        MemberEntity  miEntity = new MemberEntity(); 
=======
    public Map<String, Object> joinOwnerMember(MemberInfoEntity data){
        MemberInfoEntity  miEntity = new MemberInfoEntity(); 
>>>>>>> 34c9e0df497c5a98f09199fd1e52287aa75e61ed
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
<<<<<<< HEAD
        MemberEntity loginUser = null;
=======
        MemberInfoEntity loginUser = null;
>>>>>>> 34c9e0df497c5a98f09199fd1e52287aa75e61ed
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
<<<<<<< HEAD
    MemberEntity memberInfo = (MemberEntity)session.getAttribute("loginUser");
=======
    MemberInfoEntity memberInfo = (MemberInfoEntity)session.getAttribute("loginUser");
>>>>>>> 34c9e0df497c5a98f09199fd1e52287aa75e61ed
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
<<<<<<< HEAD
    MemberEntity memberInfo = (MemberEntity)session.getAttribute("loginUser");
=======
    MemberInfoEntity memberInfo = (MemberInfoEntity)session.getAttribute("loginUser");
>>>>>>> 34c9e0df497c5a98f09199fd1e52287aa75e61ed
    // session의 miSeq로 로그인한 회원정보 끌어와서 
    // editMemberInfo로 받은 입력값만 수정
    // memberInfo = mRepo.findByMiSeq(memberInfo.getMiSeq()); // memberInfo 객체에 로그인한 회원정보 넣어주기
    // 로그인한 회원정보가 있다면(= session에 데이터가 있다면) 회원수정 실행
    if(memberInfo != null){
<<<<<<< HEAD
        if(memberInfo.getMiPwd() != null){
        try{String encPwd = AESAlgorithm.Encrypt(editMemberInfo.getPwd());
            String checkEncPwd = AESAlgorithm.Encrypt(editMemberInfo.getCheckPwd());
            memberInfo.setMiPwd(encPwd);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        if(editMemberInfo.getName() != null){
            memberInfo.setMiName(editMemberInfo.getName());
        }
        if(editMemberInfo.getNickName() != null){
            memberInfo.setMiNickname(editMemberInfo.getNickName());
        }
        if(editMemberInfo.getPhoneNum() != null){
            memberInfo.setMiName(editMemberInfo.getPhoneNum());
        }
        if(editMemberInfo.getAdress() != null){
            memberInfo.setMiName(editMemberInfo.getAdress());
        }
        if(editMemberInfo.getDetailAdress() != null){
            memberInfo.setMiName(editMemberInfo.getDetailAdress());
        }
=======
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
>>>>>>> 34c9e0df497c5a98f09199fd1e52287aa75e61ed
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
<<<<<<< HEAD
    }


    // 회원탈퇴(상태값 변경)
    // 회원 상태값(1. 기본 2. 정지 3.탈퇴)
    public Map<String, Object> deleteMemberifo(HttpSession session){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        MemberEntity memberInfo = (MemberEntity)session.getAttribute("loginUser");
        if(memberInfo != null){
            memberInfo.setMiStatus(3);
            mRepo.save(memberInfo);
            resultMap.put("status", true);
            resultMap.put("message", "탈퇴되었습니다.");
            resultMap.put("code", HttpStatus.OK);
        }
        else{
            resultMap.put("status", false);
            resultMap.put("message", "로그인을 해주세요.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        return resultMap;
    }

    //로그아웃
    public Map<String, Object> logOut(HttpSession session){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        MemberEntity memberInfo = (MemberEntity)session.getAttribute("loginUser");
        if(memberInfo != null){
            session.invalidate();
            resultMap.put("status", true);
            resultMap.put("message", "로그아웃 되었습니다.");
            resultMap.put("code", HttpStatus.OK);
        }
        else if(memberInfo == null){
            resultMap.put("status", false);
            resultMap.put("message", "로그인을 해주세요.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        return resultMap;
    }

   // 중복체크 기능(id, phoneNum, nickName, businessNum)
   // content = 입력받을 내용 , type = 중복검사할 타입(id, phoneNum, nickName)
   public Map<String, Object> checkDuplicated(String content, String type){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        Integer i = 0;
        if(type.equals("id")){
            i = mRepo.countBymiId(content);
            if(i != 0){
            resultMap.put("status", false);
            resultMap.put("message", "같은 아이디가 있어요.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            }
        }   
        if(type.equals("phoneNum")){
            i = mRepo.countBymiPhoneNum(content);
            if(i != 0){
            resultMap.put("status", false);
            resultMap.put("message", "이미 가입된 회원이에요(전화번호가 중복).");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            }
        }   
        if(type.equals("nickName")){
            i = mRepo.countBymiNickname(content);
            if(i != 0){
            resultMap.put("status", false);
            resultMap.put("message", "같은 닉네임이 있어요.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            }
        }   
        if(type.equals("businessNum")){
            i = mRepo.countBymiBusinessNum(content);
            if(i != 0){
            resultMap.put("status", false);
            resultMap.put("message", "같은 사업자 번호가 있어요.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            }
        }   
        else{
            resultMap.put("status", false);
            resultMap.put("message", "중복검사할 내용(content) 과 타입(id, phoneNum, nickName, businessNum) 을 확인해주세요.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        return resultMap;
    }
=======
}
>>>>>>> 34c9e0df497c5a98f09199fd1e52287aa75e61ed
}  
    
    

