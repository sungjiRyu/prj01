package com.project1st.starbucks.admin.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.project1st.starbucks.admin.entity.MemberEntity;
import com.project1st.starbucks.admin.entity.StoreEntity;
import com.project1st.starbucks.admin.repository.MemberInfoRepository;
import com.project1st.starbucks.admin.repository.StoreRepository;
import com.project1st.starbucks.member.DTO.PostFindPwdDTO;
import com.project1st.starbucks.member.DTO.GetLoginUserInfoDTO;
import com.project1st.starbucks.member.DTO.PostAuthNumByEmailDTO;
import com.project1st.starbucks.member.DTO.PostFindIdDTO;
import com.project1st.starbucks.member.DTO.PostLoginDTO;
import com.project1st.starbucks.member.DTO.PutEditMemberInfoDTO;
import com.project1st.starbucks.util.AESAlgorithm;
import com.project1st.starbucks.util.SendMail;
import com.project1st.starbucks.util.SendMessage;


import com.project1st.starbucks.util.GetAuthNum;
import com.project1st.starbucks.util.GetTempPwd;

import jakarta.servlet.http.HttpSession;
// import net.nurigo.java_sdk.api.Message;

@Service
public class MemberService {
    // MemberEntity loginUser = new MemberEntity();
    @Autowired MemberInfoRepository mRepo;
    @Autowired SendMail sendMail;
    @Autowired SendMessage sendMessage;
    @Autowired GetAuthNum getAuthNum;
    @Autowired GetTempPwd getTempPwd;
    @Autowired StoreRepository sRepo;
//    " ^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d~!@#$%^&*()+|=]{8,20}$"

    // 일반회원가입 메소드
    public Map<String, Object> joinNomalMember(MemberEntity data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        String pwdPattern =  "^[a-zA-Z\\d`~!@#$%^&*()-_=+]{8,20}$"; // 영어 및 숫자를 허용하며, 숫자키와 관련된 특수문자만 허용한다
        String emailPattern = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$"; // 이메일 정규식 패턴
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
            resultMap.put("message", "비밀번호는 영문과 숫자를 포함하여 8~20자여야 합니다.");
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

        
      
    
    // 점주회원가입 메소드
    public Map<String, Object> joinOwnerMember(MemberEntity data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        String pwdPattern =  "^[a-zA-Z\\d`~!@#$%^&*()-_=+]{8,20}$"; // 비밀번호는 영문과 숫자를 포함하여 8자 이상이어야 합니다.
        StoreEntity storeInfo = null;
        Integer i = mRepo.countBymiBusinessNum(data.getMiBusinessNum());
        try {
            storeInfo = sRepo.findBySbiBusinessAddress(data.getMiBusinessNum());
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            resultMap.put("message", "비밀번호는 영문과 숫자를 포함하여 8~20자여야 합니다.");
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
        else if (i != 0) {
            resultMap.put("status", false);
            resultMap.put("message", "같은 사업자 번호가 있어요.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        else if (storeInfo == null) {
            resultMap.put("status", false);
            resultMap.put("message", "등록되지 않은 사업자등록번호입니다.");
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
                resultMap.put("message", "비밀번호는 영문과 숫자를 포함하여 8~20자여야 합니다.");
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
        MemberEntity memberInfo = null;
        try {
            memberInfo = mRepo.findByMiIdAndMiPwd(data.getMiId(), AESAlgorithm.Encrypt(data.getMiPwd()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 회원정보가 없을때(탈퇴회원도 동일)
        if (memberInfo == null || memberInfo.getMiStatus() == 3) {
            resultMap.put("status", false);
            resultMap.put("message", "아이디 혹은 비밀번호 오류입니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        // 회원 상태값 2일때 (정지된 회원)
        else if (memberInfo.getMiStatus() == 2) {
            resultMap.put("status", false);
            resultMap.put("message", "정지된 회원입니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        // 회원 상태값 1일때(정상로그인)
        else {
            // String jwtToken = Jwt.createJwt(data.getMiId());
            // resultMap.put("jwt", jwtToken);
            GetLoginUserInfoDTO loginUserInfo = GetLoginUserInfoDTO.builder()
            .miSeq(memberInfo.getMiSeq())
            .miId(memberInfo.getMiId())
            .miName(memberInfo.getMiName())
            .miNickname(memberInfo.getMiNickname())
            .miBirth(memberInfo.getMiBirth())
            .miGen(memberInfo.getMiGen())
            .miPhoneNum(memberInfo.getMiPhoneNum())
            .miStatus(memberInfo.getMiStatus())
            .miRegDate(memberInfo.getMiRegDate())
            .miGroup(memberInfo.getMiGroup())
            .miAddress(memberInfo.getMiAddress())
            .miDetailAddress(memberInfo.getMiDetailAddress())
            .miLastLogin(memberInfo.getMiLastLogin())
            .miSbiSeq(memberInfo.getMiSbiSeq())
            .miBusinessNum(memberInfo.getMiBusinessNum())
            .build(); 

            resultMap.put("data", loginUserInfo);
            resultMap.put("status", true);
            resultMap.put("message", "로그인 되었습니다");
            resultMap.put("code", HttpStatus.ACCEPTED);
        }
        return resultMap;
    }
    
    // 로그인한 회원 정보 조회
    public Map<String, Object> showLoginMemberInfo(@RequestParam Long miSeq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        MemberEntity memberInfo = mRepo.findByMiSeq(miSeq);
        if (memberInfo != null) {
            memberInfo = mRepo.findByMiSeq(memberInfo.getMiSeq());
            resultMap.put("status", true);
            resultMap.put("message", "현재 로그인한 사용자 정보");
            resultMap.put("code", HttpStatus.ACCEPTED);
            resultMap.put("memberInfo", memberInfo);
            return resultMap;
        } else {
            
            resultMap.put("status", false);
            resultMap.put("message", "먼저 로그인을 해주세요.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            return resultMap;
        }
    }
    
    // 회원정보 수정
    // editMemberInfo 통해서 수정할 회원정보 받아옴
    // session 에담긴 회원seq 통해서 수정할 회원정보 찾기

    public Map<String, Object> editMemberInfo(PutEditMemberInfoDTO editMemberInfo, Long miSeq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        // session의 로그인 정보를 memberInfo에 담아둠
        MemberEntity memberInfo = null;
        try {
            memberInfo = mRepo.findByMiSeq(miSeq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // session의 miSeq로 로그인한 회원정보 끌어와서
        // editMemberInfo로 받은 입력값만 수정
        // memberInfo = mRepo.findByMiSeq(memberInfo.getMiSeq()); // memberInfo 객체에 로그인한
        // 회원정보 넣어주기
        // 로그인한 회원정보가 있다면(= session에 데이터가 있다면) 회원수정 실행
        if (memberInfo != null) {
            if (memberInfo.getMiPwd() != null) {
                try {
                    String encPwd = AESAlgorithm.Encrypt(editMemberInfo.getMiPwd());
                    // String checkEncPwd = AESAlgorithm.Encrypt(editMemberInfo.getCheckPwd());
                    memberInfo.setMiPwd(encPwd);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (editMemberInfo.getMiName() != null) {
                memberInfo.setMiName(editMemberInfo.getMiName());
            }
            if (editMemberInfo.getMiNickname() != null) {
                memberInfo.setMiNickname(editMemberInfo.getMiNickname());
            }
            if (editMemberInfo.getMiPhoneNum() != null) {
                memberInfo.setMiPhoneNum(editMemberInfo.getMiPhoneNum());
            }
            if (editMemberInfo.getMiAdress() != null) {
                memberInfo.setMiAddress(editMemberInfo.getMiAdress());
            }
            if (editMemberInfo.getMiDetailAdress() != null) {
                memberInfo.setMiDetailAddress(editMemberInfo.getMiDetailAdress());
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
    public Map<String, Object> deleteMemberinfo(Long miSeq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        MemberEntity memberInfo = mRepo.findByMiSeq(miSeq);
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
    public Map<String, Object> logOut(Long miSeq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        MemberEntity memberInfo = mRepo.findByMiSeq(miSeq);
        if (memberInfo != null) {
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
    
    // 유효성 체크(id, phoneNum, nickName, businessNum, pwd)
    // content = 입력받을 내용 , type = 중복검사할 타입(id, phoneNum, nickName, businessNum)
    public Map<String, Object> checkDuplicated(String type, String content) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        String pwdPattern =  "^[a-zA-Z\\d`~!@#$%^&*()-_=+]{8,20}$"; // 비밀번호는 영문과 숫자를 포함하여 8자 이상이어야 합니다.
        String emailPattern = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$"; // 비밀번호는 영문과 숫자를 포함하여 8자 이상이어야 합니다.
        // else if(!Pattern.matches(emailPattern, data.getMiId())){
            // resultMap.put("status", false);
            // resultMap.put("message", "이메일 형식에 맞는 아이디를 입력해주세요");
            // resultMap.put("code", HttpStatus.BAD_REQUEST);
        // }
        Integer i = 0;
        if (type.equals("miId")) {
            i = mRepo.countBymiId(content);
            if (i != 0) {
                resultMap.put("status", false);
                resultMap.put("message", "같은 아이디가 있어요.");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
            } else if(!Pattern.matches(emailPattern, content)){
                resultMap.put("status", false);
                resultMap.put("message", "이메일 형식에 맞는 아이디를 사용해주세요.");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
            }
            else{
                resultMap.put("status", true);
                resultMap.put("message", "사용할 수 있는 아이디 입니다.");
                resultMap.put("code", HttpStatus.OK);
            }
        } else if (type.equals("miPhoneNum")) {
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
        } else if (type.equals("miNickname")) {
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
        } else if (type.equals("miBusinessNum")) {
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
        } 
        else if(type.equals("miPwd")){
                if(!Pattern.matches(pwdPattern, content)){
                resultMap.put("status", false);
                resultMap.put("message", "비밀번호는 영문과 숫자를 포함하여 8~20자여야 합니다.");
                }
                else{
                resultMap.put("status", false);
                resultMap.put("message", "사용할 수 있는 비밀번호 입니다.");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
                }
            }
        else{
            resultMap.put("miStatus", false);
            resultMap.put("message", "유효성 검사할 타입(id, phoneNum, nickName, businessNum, pwd) 을 확인해주세요.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        return resultMap;
    }


    // ===============================================  < 아이디 찾기 > ===========================================================

    // 아이디 찾기 인증번호 발송(휴대폰)
    public Map<String, Object> IdAuthNumByPhone(PostFindIdDTO data, HttpSession session) {
        MemberEntity user = null;
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String name = data.getMiName();
        String phoneNum = data.getMiPhoneNum();
        try {
            user = mRepo.findByMiNameAndMiPhoneNum(data.getMiName(), data.getMiPhoneNum());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 회원정보가 없을때(탈퇴회원도 동일)
        if(name == null){
            resultMap.put("status", false);
            resultMap.put("message", "이름을 입력해주세요.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        else if(phoneNum == null){
            resultMap.put("status", false);
            resultMap.put("message", "전화번호를 입력해 주세요.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        else if (user == null || user.getMiStatus() == 3) {
            resultMap.put("status", false);
            resultMap.put("message", "등록된 회원정보와 일치하지 않습니다");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        else{
            // 인증번호 생성
            Integer certificationNum = GetAuthNum.getAuthNum();
            // 인증번호 메시지로 발송
            sendMessage.sendAuthNumbyPhone(data.getMiPhoneNum(), certificationNum);

            // 입력받은 이름, 전화번호와 일치하는 사용자 정보 seesion에 저장
            // 생성한 인증번호 session 에 저장
            // session에 저장된 정보는 3분후 삭제
            session.setAttribute("authNum", certificationNum);
            session.setAttribute("user", user);
            session.setMaxInactiveInterval(60*3);

            resultMap.put("status", true);
            resultMap.put("message", "인증번호가 발송되었습니다. 3분안에 입력해 주세요");
            resultMap.put("code", HttpStatus.OK);
            resultMap.put("authNum", certificationNum);

        }
        return resultMap;
    }
    
    // // 아이디찾기 인증번호 발송(이메일)
    // public Map<String, Object> IdAuthNumByEmail(PostAuthNumByEmailDTO data, HttpSession session){
    //     Map<String, Object> resultMap = new HashMap<String, Object>();
    //     MemberEntity user = null;
    //     try {
    //         user = mRepo.findByMiName(data.getMiName());
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    //     if (user == null || user.getMiStatus() == 3) {
    //         resultMap.put("status", false);
    //         resultMap.put("message", "등록되지 않은 이름입니다.");
    //         resultMap.put("code", HttpStatus.BAD_REQUEST);
    //     }
    //     else if(data.getMiName() == null){
    //         resultMap.put("status", false);
    //         resultMap.put("message", "이름을 입력해주세요.");
    //         resultMap.put("code", HttpStatus.BAD_REQUEST);
    //     }
    //     else if(data.getMiEmail() == null){
    //         resultMap.put("status", false);
    //         resultMap.put("message", "인증번호를 받을 이메일을 입력해주세요.");
    //         resultMap.put("code", HttpStatus.BAD_REQUEST);
    //     }
    //     else{
    //          // 인증번호 생성
    //          Integer certificationNum = GetAuthNum.getAuthNum();
    //          // 인증번호 이메일로 발송
    //          sendMail.sendMail(data.getMiEmail(), certificationNum);
 
    //          // 입력받은 이름, 전화번호와 일치하는 사용자 정보 seesion에 저장
    //          // 생성한 인증번호 session 에 저장
    //          // session에 저장된 정보는 3분후 삭제
    //          session.setAttribute("user", user);
    //          session.setAttribute("authNum", certificationNum);
    //          session.setMaxInactiveInterval(60 * 3);
 
    //          resultMap.put("status", true);
    //          resultMap.put("message", "인증번호가 발송되었습니다. 3분안에 입력해 주세요");
    //          resultMap.put("code", HttpStatus.OK);
    //          resultMap.put("authNum", certificationNum);
    //     }
    //     return resultMap;
    // }


    // 인증번호 일치할시 아이디 보여줌
    public Map<String, Object> findId(HttpSession session, Integer authNum){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        MemberEntity memberInfo = (MemberEntity) session.getAttribute("user");
        Integer authCode = (Integer) session.getAttribute("authNum");
        // Integer authCode = Integer.parseInt(String.valueOf(session.getAttribute("authNum")));
        // int num = Integer.parseInt(String.valueOf(map.get("bno")));
        // try{
        if(authCode != null && authCode.equals(authNum)){
            MemberEntity memberId = mRepo.findByMiPhoneNum(memberInfo.getMiPhoneNum());
            resultMap.put("status", true);
            resultMap.put("message", "인증 성공");
            resultMap.put("code", HttpStatus.OK);
            resultMap.put("memberId", memberId.getMiId());
            session.removeAttribute("authNum");
            session.removeAttribute("user");
        }
        else{
            resultMap.put("status", false);
            resultMap.put("message", "인증번호가 일치하지 않습니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            }
        // }
        // catch (Exception e) {
            // resultMap.put("status", false);
            // resultMap.put("message", "인증번호가 만료되었습니다.(인증번호는 3분안에 입력해주세요)");
            // resultMap.put("code", HttpStatus.BAD_REQUEST);
            // e.printStackTrace();

        // }
        return resultMap;
    }

    

   
        
    // ===============================================  < 비밀번호 찾기 > ===========================================================
    // 비밀번호를 찾고자하는 아이디를 입력해주세요.
    public Map<String, Object> checkId(String Id){
        Map<String, Object> resultMap = new HashMap<String, Object>();
           Integer i = mRepo.countBymiId(Id);
            if (i != 0) {
                resultMap.put("status", true);
                resultMap.put("message", "올바른 아이디 입니다.");
                resultMap.put("code", HttpStatus.OK);
            }
            else{
                resultMap.put("status", false);
                resultMap.put("message", "아이디를 찾을 수 없습니다.");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
            }
            return resultMap;
        }

    // 비밀번호찾기 휴대폰 인증 
    public Map<String, Object> PwdAuthNumByPhone(PostFindIdDTO data, HttpSession session) {
        MemberEntity user = null;
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            user = mRepo.findByMiNameAndMiPhoneNum(data.getMiName(), data.getMiPhoneNum());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 회원정보가 없을때(탈퇴회원도 동일)
        if (user == null || user.getMiStatus() == 3) {
            resultMap.put("status", false);
            resultMap.put("message", "등록된 회원정보와 일치하지 않습니다");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        } else if (data.getMiName() == null) {
            resultMap.put("status", false);
            resultMap.put("message", "이름을 입력해주세요.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        } else if (data.getMiPhoneNum() == null) {
            resultMap.put("status", false);
            resultMap.put("message", "전화번호를 입력해 주세요.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        } else {
            // 인증번호 생성
            Integer certificationNum = GetAuthNum.getAuthNum();
            // 인증번호 메시지로 발송
            sendMessage.sendAuthNumbyPhone(data.getMiPhoneNum(), certificationNum);

            // 입력받은 이름, 전화번호와 일치하는 사용자 정보 seesion에 저장
            // 생성한 인증번호 session 에 저장
            // session에 저장된 정보는 3분후 삭제
            session.setAttribute("user", user);
            session.setAttribute("authNum", certificationNum);
            session.setMaxInactiveInterval(60 * 3);

            resultMap.put("status", true);
            resultMap.put("message", "인증번호가 발송되었습니다. 3분안에 입력해 주세요");
            resultMap.put("code", HttpStatus.OK);
            resultMap.put("authNum", certificationNum);
        }
        return resultMap;
    }
    
    // 이메일 인증
    public Map<String, Object> PwdAuthNumByEmail(PostFindPwdDTO data, HttpSession session){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        MemberEntity user = null;
        String id = data.getMiId();
        String name= data.getMiName();
        try {
            user = mRepo.findByMiNameAndMiId(data.getMiName(), data.getMiId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (name == null) {
            resultMap.put("status", false);
            resultMap.put("message", "이름을 입력해주세요.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        } else if (id == null) {
            resultMap.put("status", false);
            resultMap.put("message", "아이디(이메일)를 입력해주세요.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        } else if (user == null || user.getMiStatus() == 3) {
            resultMap.put("status", false);
            resultMap.put("message", "등록된 회원정보와 일치하지 않습니다");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        else{
             // 인증번호 생성
             Integer certificationNum = GetAuthNum.getAuthNum();
             // 인증번호 이메일로 발송
             sendMail.sendMail(data.getMiId(), certificationNum);
 
             // 입력받은 이름, 전화번호와 일치하는 사용자 정보 seesion에 저장
             // 생성한 인증번호 session 에 저장
             // session에 저장된 정보는 3분후 삭제
             session.setAttribute("user", user);
             session.setAttribute("authNum", certificationNum);
             session.setMaxInactiveInterval(60 * 3);
 
             resultMap.put("status", true);
             resultMap.put("message", "인증번호가 발송되었습니다. 3분안에 입력해 주세요");
             resultMap.put("code", HttpStatus.OK);
             resultMap.put("authNum", certificationNum);
             
        }
        return resultMap;
    }



    // 이메일or휴대폰으로 로 보낸 인증번호가 일치할 시 임시비밀번호 발급하기
    public Map<String, Object> getTempPwd(HttpSession session, Integer authNum){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        MemberEntity memberInfo = (MemberEntity) session.getAttribute("user");
        Integer authCode = (Integer) session.getAttribute("authNum");
        // Integer authCode = Integer.parseInt(String.valueOf(session.getAttribute("authNum")));
        // int num = Integer.parseInt(String.valueOf(map.get("bno")));
        try{
        
        // 인증번호가 일치한다면  
        if(authCode.equals(authNum)){
            // 임시비밀번호 발급
            String newPwd = getTempPwd.getRandomPwd();
            // 저장된 회원의 비밀번호를 임시빌번호로 바꿈
            memberInfo.setMiPwd(AESAlgorithm.Encrypt(newPwd));
            mRepo.save(memberInfo);
            // 이메일(등록된 아이디)로 임시비밀번호 발송
            sendMail.sendMail(memberInfo.getMiId(), newPwd);
            session.removeAttribute("user");
            session.removeAttribute("authNum");
            resultMap.put("status", true);
            resultMap.put("message", "등록된 아이디(이메일)로 임시비밀번호를 보내드렸어요");
            resultMap.put("code", HttpStatus.OK);
        }
        else{
            resultMap.put("status", false);
            resultMap.put("message", "인증번호가 일치하지 않습니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e) {
            // resultMap.put("status", false);
            // resultMap.put("message", "인증번호가 만료되었습니다.(3분안에 입력해주세요)");
            // resultMap.put("code", HttpStatus.BAD_REQUEST);
            e.printStackTrace();
        }
        return resultMap;
    }

}
