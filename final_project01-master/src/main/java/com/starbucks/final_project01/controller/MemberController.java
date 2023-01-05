package com.starbucks.final_project01.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.starbucks.final_project01.DTO.PostLoginDTO;
import com.starbucks.final_project01.entity.MemberInfoEntity;
import com.starbucks.final_project01.service.MemberService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired MemberService mService;
    // 회원가입 api
    @PostMapping("/join")
    public ResponseEntity<Object> memberJoin(@RequestBody MemberInfoEntity data){
        Map<String, Object> resultMap = mService.joinMember(data);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }
  
    // 
    @GetMapping("/main")
    public ResponseEntity<Object> main(){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("message", "main");
        return new ResponseEntity<Object>(resultMap, HttpStatus.OK);
    }
    

    // 로그인 api
    @PostMapping("/login")
    public ResponseEntity<Object> postLogin(@RequestBody PostLoginDTO data, HttpSession session){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap = mService.loginMembre(data);
        session.setAttribute("loginUser", resultMap.get("loginUser"));
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }
    
    
    
}
