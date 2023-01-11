package com.starbucks.final_project01.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
<<<<<<< HEAD
import org.springframework.ui.Model;
=======
>>>>>>> 34c9e0df497c5a98f09199fd1e52287aa75e61ed
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.PutMapping;
=======
>>>>>>> 34c9e0df497c5a98f09199fd1e52287aa75e61ed
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.starbucks.final_project01.DTO.PostLoginDTO;
import com.starbucks.final_project01.DTO.PutEditMemberInfoDTO;
<<<<<<< HEAD
import com.starbucks.final_project01.entity.MemberEntity;
=======
import com.starbucks.final_project01.entity.MemberInfoEntity;
>>>>>>> 34c9e0df497c5a98f09199fd1e52287aa75e61ed
import com.starbucks.final_project01.service.MemberService;

import jakarta.servlet.http.HttpSession;

@RestController
// @RequestMapping("/member")
public class MemberController {
    @Autowired MemberService mService;
    // 일반회원가입 api
    @PostMapping("/member/join")
<<<<<<< HEAD
    public ResponseEntity<Object> nomalMemberJoin(@RequestBody MemberEntity data){
=======
    public ResponseEntity<Object> nomalMemberJoin(@RequestBody MemberInfoEntity data){
>>>>>>> 34c9e0df497c5a98f09199fd1e52287aa75e61ed
        Map<String, Object> resultMap = mService.joinNomalMember(data);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }
    // 점주 회원 가입 api
    @PostMapping("owner/join")
<<<<<<< HEAD
    public ResponseEntity<Object> ownerMemberJoin(@RequestBody com.starbucks.final_project01.entity.MemberEntity data){
=======
    public ResponseEntity<Object> ownerMemberJoin(@RequestBody MemberInfoEntity data){
>>>>>>> 34c9e0df497c5a98f09199fd1e52287aa75e61ed
        Map<String, Object> resultMap = mService.joinOwnerMember(data);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }   

    // 중복검사 API
    @GetMapping("/idcheck")
    public ResponseEntity<Object> memberCheck(@RequestParam String id){
        Map<String, Object> resultMap = mService.checkIdDuplicat(id);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }

    // 로그인 API(회원 상태값(1. 기본 2. 정지 3.탈퇴))
    @PostMapping("/login")
    public ResponseEntity<Object> postLogin(@RequestBody PostLoginDTO data, HttpSession session){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap = mService.loginMember(data);
        session.setAttribute("loginUser", resultMap.get("loginUser"));
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }

    // 로그인 회원정보 조회
    @GetMapping("/member/myinfo")
    public ResponseEntity<Object> showMyinfo(HttpSession session){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap = mService.showLoginMemberInfo(session);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }

    // 회원 정보 수정
    @PatchMapping("/member/edit")
    public ResponseEntity<Object> editMemberInfo(@RequestBody PutEditMemberInfoDTO data, HttpSession session){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap = mService.editMemberInfo(session, data);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }
<<<<<<< HEAD

    // 회원 탈퇴
    @PatchMapping("/member/leave")
    public ResponseEntity<Object> deleteMemberInfo(HttpSession session){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap = mService.deleteMemberifo(session);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }

    // 로그아웃
    @PostMapping("/member/logout")
    public ResponseEntity<Object> memberLogout(HttpSession session){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap = mService.logOut(session);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }

    // 중복체크(id, phoneNum, nickName, businessNum)
    @GetMapping("/member/{type}/{content}")
    public ResponseEntity<Object> checkDuplicated(@PathVariable String type, @PathVariable String content){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap = mService.checkDuplicated(type, content);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }
=======
>>>>>>> 34c9e0df497c5a98f09199fd1e52287aa75e61ed
}
