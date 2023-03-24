package com.project1st.starbucks.membershipcard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    //카드생성 -> 완료 ♥
    @PutMapping("/new")
    public ResponseEntity<Object> postNewMembershipCard(@RequestBody MembershipCardEntity data, @RequestParam Long memberNo) throws Exception {
        return cardService.createNewMembershipCard(data, memberNo);
    }


    //카드충전 (QR코드 인식 후) -> 완료 ♥
    @PatchMapping("/charge")
    public ResponseEntity<Object> patchChargeMembershipCard(@RequestParam Integer money, @RequestParam Long memberNo) {
        return cardService.chargeMembershipCard(money, memberNo);
    }
    

    //카드 이미지 저장 -> 완료 ♥
    @GetMapping("/image/{uri}")
    public ResponseEntity<Resource> getCardQRImage(@PathVariable String uri, HttpServletRequest request) throws Exception {
        return cardService.getCardQRImage(uri, request);
    }


    //카드조회 -> 완료 ♥
    @GetMapping("/detail")
    public ResponseEntity<Object> getMembershipCard(@RequestParam Long memberNo) {
        return cardService.detailMembershipCard(memberNo);
    }
    

    //카드삭제 -> 완료 ♥
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteMembershipCard(@RequestParam Long memberNo) {
        return cardService.deleteMembershipCard(memberNo);
    }



}
