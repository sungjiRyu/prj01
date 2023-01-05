package com.starbucks.final_project01.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.starbucks.final_project01.DTO.PostLoginDTO;
import com.starbucks.final_project01.entity.MemberInfoEntity;
import com.starbucks.final_project01.repository.MemberInfoRepository;
import com.starbucks.final_project01.util.AESAlgorithm;

import jakarta.servlet.http.HttpSession;

@Service
// 회원가입 메소드
public class MemberService {
    @Autowired MemberInfoRepository mRepo;

    public Map<String, Object> joinMember(MemberInfoEntity data){
           Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    // 휴대폰 번호 판별해서 중복가입 방지 
    if(mRepo.countByphoneNum(data.getPhoneNum()) == 1){
    resultMap.put("status",false);
    resultMap.put("message", "이미 등록된 사용자 입니다.");
    resultMap.put("code", HttpStatus.BAD_REQUEST);
    }
    else{
        try{
            String encPwd = AESAlgorithm.Encrypt(data.getMiPwd());
            data.setMiPwd(encPwd);
        } 
        catch(Exception e) {e.printStackTrace();}
        mRepo.save(data);
        resultMap.put("status", true);
        resultMap.put("message", "회원이 등록되었습니다.");
        resultMap.put("code", HttpStatus.CREATED);
    }
    return resultMap;
}
    
// 로그인 메소드
public Map<String, Object> loginMembre(PostLoginDTO data){
    Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    MemberInfoEntity loginUser = null;
        try {
            loginUser = mRepo.findByMiIdAndMiPwd(data.getId(), AESAlgorithm.Encrypt(data.getPwd()));
        } catch(Exception e) {e.printStackTrace();}
        if(loginUser == null) {
            resultMap.put("status", false);
            resultMap.put("message", "아이디 혹은 비밀번호 오류입니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        } else {
            resultMap.put("status", true);
            resultMap.put("message", "로그인 되었습니다");
            resultMap.put("code", HttpStatus.ACCEPTED);
            resultMap.put("loginUser", loginUser);
        }
        return resultMap;
    }
    
}


    



    

