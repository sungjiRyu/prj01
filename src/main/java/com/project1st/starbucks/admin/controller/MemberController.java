package com.project1st.starbucks.admin.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project1st.starbucks.admin.entity.MemberEntity;
import com.project1st.starbucks.admin.service.MemberService;
import com.project1st.starbucks.admin.service.session;
import com.project1st.starbucks.member.DTO.PostAuthNumByEmailDTO;
import com.project1st.starbucks.member.DTO.PostFindIdDTO;
import com.project1st.starbucks.member.DTO.PostFindPwdDTO;
import com.project1st.starbucks.member.DTO.PostLoginDTO;
import com.project1st.starbucks.member.DTO.PutEditMemberInfoDTO;

import jakarta.persistence.metamodel.SetAttribute;
import jakarta.servlet.http.HttpSession;

// 프론트에서 세션스토리지 쓴다고함
// 백에서는 프론트에서 보낸 회원정보 파라미터로 받아서 api 동작
// 그러면 백에서는 세션쓸 필요가 없음
// 세션 


@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired MemberService mService;
    // 일반회원가입 api
    @PostMapping("/join")
    public ResponseEntity<Object> nomalMemberJoin(@RequestBody MemberEntity data){
        Map<String, Object> resultMap = mService.joinNomalMember(data);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }
    // 점주 회원 가입 api
    @PostMapping("/store/join")
    public ResponseEntity<Object> ownerMemberJoin(@RequestBody MemberEntity data){
        Map<String, Object> resultMap = mService.joinOwnerMember(data);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }   

    // 로그인 API (회원 상태값(1. 기본 2. 정지 3.탈퇴))
    @PostMapping("/login")
    public ResponseEntity<Object> postLogin(@RequestBody PostLoginDTO data){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap = mService.loginMember(data);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }

    // 로그인 회원정보 조회
    @GetMapping("/myinfo")
    public ResponseEntity<Object> showMyinfo(@RequestParam Long miSeq){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap = mService.showLoginMemberInfo(miSeq);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }

    // 회원 정보 수정
    @PatchMapping("/edit")
    public ResponseEntity<Object> editMemberInfo(@RequestBody PutEditMemberInfoDTO data, @RequestParam Long miSeq){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap = mService.editMemberInfo(data, miSeq);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }

    // 회원 탈퇴
    @PatchMapping("/leave")
    public ResponseEntity<Object> deleteMemberInfo(@RequestParam Long miSeq){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap = mService.deleteMemberinfo(miSeq);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }

    // 로그아웃은 프론트에서
    // 로그아웃
    // @PostMapping("/logout")
    // public ResponseEntity<Object> memberLogout(@RequestParam Long miSeq){
    //     Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    //     resultMap = mService.logOut(miSeq);
    //     return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    // }

    // 유효성 체크(id, phoneNum, nickName, businessNum, pwd)
    @GetMapping("/{type}/{content}")
    public ResponseEntity<Object> checkDuplicated(@PathVariable String type, @PathVariable String content){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap = mService.checkDuplicated(type, content);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }

    // ===============================================  < 아이디 찾기 > ===========================================================

    // 전화번호로 인증번호 발송
    @PostMapping("/findid/phone")
    public ResponseEntity<Object> findIdByPhone(@RequestBody PostFindIdDTO data, HttpSession session){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap = mService.IdAuthNumByPhone(data, session);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }

    // // 이메일로 인증번호 발송
    // @PostMapping("/findid/email")
    // public ResponseEntity<Object> findIdByEmail(@RequestBody PostAuthNumByEmailDTO data, HttpSession session){
    //     Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    //     resultMap = mService.IdAuthNumByEmail(data, session);
    //     return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    // }

    // 인증번호 일치시 아이디 보여줌
    @GetMapping("/findid")
    public ResponseEntity<Object> findId(HttpSession session, @RequestParam Integer authNum){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap = mService.findId(session, authNum);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }

    // ===============================================  < 비밀번호 찾기 > ===========================================================

    // 비밀번호를 찾고자하는 아이디를 입력해주세요
    @GetMapping("/findpwd/checkid")
    public ResponseEntity<Object> checkId(@RequestParam String miId){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap = mService.checkId(miId);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }

    // 전화번호로 인증번호 발송
    @PostMapping("/findpwd/phone")
    public ResponseEntity<Object> findPwdByPhone(@RequestBody PostFindIdDTO data, HttpSession session){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap = mService.PwdAuthNumByPhone(data, session);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }

    // // 이메일로 인증번호 발송
    // @PostMapping("/findpwd/email")
    // public ResponseEntity<Object> findPwdByEmail(@RequestBody PostFindPwdDTO data, HttpSession session){
    //     Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    //     resultMap = mService.PwdAuthNumByEmail(data, session);
    //     return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    // }

    // 인증번호 일치할시 임시비밀번호 발급
    @GetMapping("/findpwd")
    public ResponseEntity<Object> getTempPwd(@RequestParam Integer authNum, HttpSession session){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap = mService.getTempPwd(session, authNum);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }
}