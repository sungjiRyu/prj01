package com.project1st.starbucks.admin.service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.project1st.starbucks.admin.entity.MemberEntity;
import com.project1st.starbucks.admin.repository.MemberInfoRepository;
import com.project1st.starbucks.member.DTO.PostLoginDTO;
import com.project1st.starbucks.member.DTO.PutEditMemberInfoDTO;
import com.project1st.starbucks.util.AESAlgorithm;

import jakarta.servlet.http.HttpSession;

@Service
public class MemberService {
    // MemberEntity loginUser = new MemberEntity();
    @Autowired
    MemberInfoRepository mRepo;

    // 일반회원가입 메소드
    public Map<String, Object> joinNomalMember(MemberEntity data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        Integer i = 0;
        String pwdPattern = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$"; // 비밀번호는 영문과 특수문자 숫자를 포함하며 8자 이상이어야 합니다
        String emailPattern = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$"; // 비밀번호는 영문과 특수문자 숫자를 포함하며 8자 이상이어야 합니다
        // 유효성 검사(필수정보 공백x, 중복가입 방지)
        
        if(data.getMiId()==null){
            resultMap.put("status", false);
            resultMap.put("message", "아이디를 입력해주세요");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        else if(!Pattern.matches(emailPattern, data.getMiId())){
            resultMap.put("status", false);
            resultMap.put("message", "이메일 형식에 맞는 아이디를 입력해주세요");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        else if(mRepo.countBymiId(data.getMiId()) != 0){
            resultMap.put("status", false);
            resultMap.put("message", "똑같은 아이디가 있어요");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        else if(data.getMiPwd()==null){
            resultMap.put("status", false);
            resultMap.put("message", "비밀번호를 입력해주세요");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        else if(!Pattern.matches(pwdPattern, data.getMiPwd())){
            resultMap.put("status", false);
            resultMap.put("message", "비밀번호는 영문과 특수문자, 숫자를 포함하여 8~20자여야 합니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            }
        else if(data.getMiName() ==null ){
                resultMap.put("status", false);
                resultMap.put("message", "이름을 입력해주세요.");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
            }
        else if(data.getMiNickname()==null){
            resultMap.put("status", false);
            resultMap.put("message", "닉네임을 입력해주세요");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        else if(mRepo.countBymiId(data.getMiNickname()) != 0){
            resultMap.put("status", false);
            resultMap.put("message", "똑같은 닉네임이 있어요");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        else if(data.getMiBirth()==null){
            resultMap.put("status", false);
            resultMap.put("message", "생년월일을 입력해 주세요(yyyy-mm-dd)");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        else if(data.getMiPhoneNum()==null){
            resultMap.put("status", false);
            resultMap.put("message", "전화번호를 입력해 주세요.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        else if ((data.getMiBusinessNum()) != null) {
            resultMap.put("status", false);
            resultMap.put("message", "일반회원은 사업자번호를 입력할 수 없습니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        // 휴대폰 번호 판별해서 중복가입 방지
        else if (mRepo.countBymiPhoneNum(data.getMiPhoneNum()) == 1) {
            resultMap.put("status", false);
            resultMap.put("message", "이미 등록된 사용자 입니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        else if ((data.getMiAddress()) == null) {
            resultMap.put("status", false);
            resultMap.put("message", "주소를 입력해 주세요(00구-00동).");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        else if ((data.getMiDetailAddress()) == null) {
            resultMap.put("status", false);
            resultMap.put("message", "상세주소를 입력해 주세요.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        // 유효성 검사를 다 통과했다면
        else {
            // 비밀번호 암호화
            try {
                String encPwd = AESAlgorithm.Encrypt(data.getMiPwd());
                data.setMiPwd(encPwd);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //
            // 입력받은 데이터 DB에 저장(MemberInfo)
            mRepo.save(data);
            resultMap.put("status", true);
            resultMap.put("message", "회원이 등록되었습니다.");
            resultMap.put("code", HttpStatus.CREATED);
            }
            return resultMap;
        }

        
        // // 아이디 중복체크
        // public Map<String, Object> checkIdDuplicat(String id) {
            //     Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    //     if (mRepo.countBymiId(id) == 1) {
    //         resultMap.put("status", false);
    //         resultMap.put("message", "중복된 아이디");
    //         resultMap.put("code", HttpStatus.CONFLICT);
    //     } else {
        //         resultMap.put("status", true);
        //         resultMap.put("message", "사용가능한 아이디");
        //         resultMap.put("code", HttpStatus.OK);
        //     }ㄹㄹㅇ눠ㅗㅎㄹㅇㄴ호ㅓㅜㅗㅎㄹㅇㄴㅁㄴ홍ㄽ43
        //     return resultMap;
    // }
    
    // 점주회원가입 메소드
    public Map<String, Object> joinOwnerMember(MemberEntity data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        String pwdPattern = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$"; // 비밀번호는 영문과 특수문자 숫자를 포함하며 8자 이상이어야 합니다
        // 휴대폰 번호 판별해서 중복가입 방지
        if(data.getMiId()==null){
            resultMap.put("status", false);
            resultMap.put("message", "아이디를 입력해주세요");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        else if(mRepo.countBymiId(data.getMiId()) != 0){
            resultMap.put("status", false);
            resultMap.put("message", "똑같은 아이디가 있어요");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        else if(data.getMiPwd()==null){
            resultMap.put("status", false);
            resultMap.put("message", "비밀번호를 입력해주세요");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        else if(!Pattern.matches(pwdPattern, data.getMiPwd())){
            resultMap.put("status", false);
            resultMap.put("message", "비밀번호는 영문과 특수문자, 숫자를 포함하여 8~20자여야 합니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            }
        else if(data.getMiName() ==null ){
                resultMap.put("status", false);
                resultMap.put("message", "이름을 입력해주세요.");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
            }
        else if(data.getMiNickname()==null){
            resultMap.put("status", false);
            resultMap.put("message", "닉네임을 입력해주세요");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        else if(mRepo.countBymiId(data.getMiNickname()) != 0){
            resultMap.put("status", false);
            resultMap.put("message", "똑같은 닉네임이 있어요");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        else if(data.getMiBirth()==null){
            resultMap.put("status", false);
            resultMap.put("message", "생년월일을 입력해 주세요(yyyy-mm-dd)");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        else if(data.getMiPhoneNum()==null){
            resultMap.put("status", false);
            resultMap.put("message", "전화번호를 입력해 주세요.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        // 휴대폰 번호 판별해서 중복가입 방지
        else if (mRepo.countBymiPhoneNum(data.getMiPhoneNum()) == 1) {
            resultMap.put("status", false);
            resultMap.put("message", "이미 등록된 사용자 입니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        else if ((data.getMiBusinessNum()) == null) {
            resultMap.put("status", false);
            resultMap.put("message", "사업자등록번호를 입력해주세요.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        else if ((data.getMiAddress()) == null) {
            resultMap.put("status", false);
            resultMap.put("message", "주소를 입력해 주세요(00구-00동).");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        else if ((data.getMiDetailAddress()) == null) {
            resultMap.put("status", false);
            resultMap.put("message", "상세주소를 입력해 주세요.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        
        // 중복되는 휴대폰 번호가 없다면
        else {
            if (!Pattern.matches(pwdPattern, data.getMiPwd())) {
                resultMap.put("status", false);
                resultMap.put("message", "비밀번호는 영문과 특수문자, 숫자를 포함하여 8~20자여야 합니다.");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
            } else {
                // 비밀번호 암호화
                try {
                    String encPwd = AESAlgorithm.Encrypt(data.getMiPwd());
                    data.setMiPwd(encPwd);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // mi_group 2로 입력 (1.일반회원 2.점주회원)
                // miEntity.setMiGroup(data.getMiGroup());
                data.setMiGroup(2);
                // 입력받은 데이터 DB에 저장(MemberInfo)
                mRepo.save(data);
                resultMap.put("status", true);
                resultMap.put("message", "회원이 등록되었습니다.");
                resultMap.put("code", HttpStatus.CREATED);
            }
        }
        return resultMap;
    }
    
    // 로그인 메소드 회원 상태값(1. 기본 2. 정지 3.탈퇴)
    public Map<String, Object> loginMember(PostLoginDTO data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        MemberEntity loginUser = null;
        try {
            loginUser = mRepo.findByMiIdAndMiPwd(data.getId(), AESAlgorithm.Encrypt(data.getPwd()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 회원정보가 없을때(탈퇴회원도 동일)
        if (loginUser == null || loginUser.getMiStatus() == 3) {
            resultMap.put("status", false);
            resultMap.put("message", "아이디 혹은 비밀번호 오류입니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        // 회원 상태값 2일때 (정지된 회원)
        else if (loginUser.getMiStatus() == 2) {
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
    public Map<String, Object> showLoginMemberInfo(HttpSession session) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        MemberEntity memberInfo = (MemberEntity) session.getAttribute("loginUser");
        if (memberInfo != null) {
            memberInfo = mRepo.findByMiId(memberInfo.getMiId());
            resultMap.put("status", true);
            resultMap.put("message", "현재 로그인한 사용자 정보");
            resultMap.put("code", HttpStatus.ACCEPTED);
            resultMap.put("memberInfo", memberInfo);
            return resultMap;
        } else {
            resultMap.put("status", false);
            resultMap.put("message", "로그인 정보가 잘못되었습니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            return resultMap;
        }
    }
    
    // 회원정보 수정
    // editMemberInfo 통해서 수정할 회원정보 받아옴
    // session 에담긴 회원seq 통해서 수정할 회원정보 찾기

    public Map<String, Object> editMemberInfo(HttpSession session, PutEditMemberInfoDTO editMemberInfo) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        // session의 로그인 정보를 memberInfo에 담아둠
        MemberEntity memberInfo = (MemberEntity) session.getAttribute("loginUser");
        // session의 miSeq로 로그인한 회원정보 끌어와서
        // editMemberInfo로 받은 입력값만 수정
        // memberInfo = mRepo.findByMiSeq(memberInfo.getMiSeq()); // memberInfo 객체에 로그인한
        // 회원정보 넣어주기
        // 로그인한 회원정보가 있다면(= session에 데이터가 있다면) 회원수정 실행
        if (memberInfo != null) {
            if (memberInfo.getMiPwd() != null) {
                try {
                    String encPwd = AESAlgorithm.Encrypt(editMemberInfo.getPwd());
                    String checkEncPwd = AESAlgorithm.Encrypt(editMemberInfo.getCheckPwd());
                    memberInfo.setMiPwd(encPwd);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (editMemberInfo.getName() != null) {
                memberInfo.setMiName(editMemberInfo.getName());
            }
            if (editMemberInfo.getNickName() != null) {
                memberInfo.setMiNickname(editMemberInfo.getNickName());
            }
            if (editMemberInfo.getPhoneNum() != null) {
                memberInfo.setMiName(editMemberInfo.getPhoneNum());
            }
            if (editMemberInfo.getAdress() != null) {
                memberInfo.setMiName(editMemberInfo.getAdress());
            }
            if (editMemberInfo.getDetailAdress() != null) {
                memberInfo.setMiName(editMemberInfo.getDetailAdress());
            }
            mRepo.save(memberInfo);
            resultMap.put("status", true);
            resultMap.put("message", "회원정보가 수정되었습니다.");
            resultMap.put("code", HttpStatus.OK);
        } else if (memberInfo == null) {
            resultMap.put("status", false);
            resultMap.put("message", "로그인을 해주세요.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        return resultMap;
    }
    
    // 회원탈퇴(상태값 변경)
    // 회원 상태값(1. 기본 2. 정지 3.탈퇴)
    public Map<String, Object> deleteMemberifo(HttpSession session) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        MemberEntity memberInfo = (MemberEntity) session.getAttribute("loginUser");
        if (memberInfo != null) {
            memberInfo.setMiStatus(3);
            mRepo.save(memberInfo);
            resultMap.put("status", true);
            resultMap.put("message", "탈퇴되었습니다.");
            resultMap.put("code", HttpStatus.OK);
        } else {
            resultMap.put("status", false);
            resultMap.put("message", "로그인을 해주세요.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        return resultMap;
    }
    
    // 로그아웃
    public Map<String, Object> logOut(HttpSession session) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        MemberEntity memberInfo = (MemberEntity) session.getAttribute("loginUser");

        if (memberInfo != null) {
            session.invalidate();
            resultMap.put("status", true);
            resultMap.put("message", "로그아웃 되었습니다.");
            resultMap.put("code", HttpStatus.OK);
        } else if (memberInfo == null) {
            resultMap.put("status", false);
            resultMap.put("message", "로그인을 해주세요.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        return resultMap;
    }
    
    // 유효성 체크(id, phoneNum, nickName, businessNum)
    // content = 입력받을 내용 , type = 중복검사할 타입(id, phoneNum, nickName, businessNum)
    public Map<String, Object> checkDuplicated(String type, String content) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        Integer i = 0;
        String pattern = "^[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣]*$"; // 특수문자, 공백 제외하고 허용
        String pwdPattern = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$"; // 비밀번호는 영문과 특수문자 숫자를 포함하며 8자 이상이어야 합니다
        if (type.equals("id")) {
            i = mRepo.countBymiId(content);
            if (i != 0) {
                resultMap.put("status", false);
                resultMap.put("message", "같은 아이디가 있어요.");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
            } else {
                resultMap.put("status", true);
                resultMap.put("message", "사용할 수 있는 아이디 입니다.");
                resultMap.put("code", HttpStatus.OK);
            }
        } else if (type.equals("phoneNum")) {
            i = mRepo.countBymiPhoneNum(content);
            if (i != 0) {
                resultMap.put("status", false);
                resultMap.put("message", "이미 가입된 회원이에요(전화번호가 중복).");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
            } else {
                resultMap.put("status", true);
                resultMap.put("message", "가입할 수 있어요(중복되는 전화번호가 없어요).");
                resultMap.put("code", HttpStatus.OK);
            }
        } else if (type.equals("nickName")) {
            i = mRepo.countBymiNickname(content);
            if (i != 0) {
                resultMap.put("status", false);
                resultMap.put("message", "같은 닉네임이 있어요.");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
            } else {
                resultMap.put("status", true);
                resultMap.put("message", "사용할 수 있는 닉네임 입니다.");
                resultMap.put("code", HttpStatus.OK);
            }
        } else if (type.equals("businessNum")) {
            i = mRepo.countBymiBusinessNum(content);
            if (i != 0) {
                resultMap.put("status", false);
                resultMap.put("message", "같은 사업자 번호가 있어요.");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
            } else {
                resultMap.put("status", true);
                resultMap.put("message", "사용할 수 있는 사업자 번호 입니다.");
                resultMap.put("code", HttpStatus.OK);
            }
        } else {
            resultMap.put("status", false);
            resultMap.put("message", "중복검사할 타입(id, phoneNum, nickName, businessNum) 을 확인해주세요.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        return resultMap;
    }
}
