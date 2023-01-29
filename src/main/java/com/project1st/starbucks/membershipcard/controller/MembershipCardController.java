package com.project1st.starbucks.membershipcard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project1st.starbucks.membershipcard.entity.MembershipCardEntity;
import com.project1st.starbucks.membershipcard.service.MembershipCardService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/card")
public class MembershipCardController {
    @Autowired MembershipCardService cardService;

    //카드생성 -> 완료 ♥ -> 진혁이 서버에서 돌아가는지 확인
    @PostMapping("/new")
    public ResponseEntity<Object> postNewMembershipCard(@RequestBody MembershipCardEntity data, HttpSession session) throws Exception {
        return cardService.createNewMembershipCard(data, session);
    }


    //카드충전 (QR코드 인식 후) 
    @PatchMapping("/charge")
    public ResponseEntity<Object> patchChargeMembershipCard(@RequestParam Integer money, HttpSession session) {
        return cardService.chargeMembershipCard(money, session);
    }
    

    //카드 이미지 저장
    @GetMapping("/image/{uri}")
    public ResponseEntity<Resource> getCardQRImage(@PathVariable String uri, HttpServletRequest request) throws Exception {
        return cardService.getCardQRImage(uri, request);
    }


    //카드조회
    @GetMapping("/detail")
    public ResponseEntity<Object> getMembershipCard(HttpSession session) {
        return cardService.detailMembershipCard(session);
    }
    

    //카드삭제 -> 완료 ♥
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteMembershipCard(HttpSession session) {
        return cardService.deleteMembershipCard(session);
    }


    //카드결제 -> 장바구니 금액보다 적으면 에러 + 장바구니 금액만큼 결제하기
    @PatchMapping("/pay")
    public ResponseEntity<Object> payMembershipCard(HttpSession session) {
        return cardService.payMembershipCard(session);
    }
}
