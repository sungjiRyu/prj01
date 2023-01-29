package com.project1st.starbucks.admin.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project1st.starbucks.admin.entity.AnnouncementEntity;
import com.project1st.starbucks.admin.entity.CouponEntity;
import com.project1st.starbucks.admin.entity.MemberEntity;
import com.project1st.starbucks.admin.repository.AnnouncementRepository;
import com.project1st.starbucks.admin.repository.CouponRepository;
import com.project1st.starbucks.admin.repository.MemberRepository;
import com.project1st.starbucks.store.entity.StoreBasicInfoEntity;
import com.project1st.starbucks.store.repository.StoreBasicInfoRepository;

@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired StoreBasicInfoRepository sRepo;
    @Autowired MemberRepository mRepo;
    @Autowired AnnouncementRepository aRepo;
    @Autowired CouponRepository cRepo;
    @GetMapping("/store")
    public ResponseEntity < Object > searchStoreBranchName(String branchName) {
        Map < String, Object > resultMap = new LinkedHashMap < String, Object > ();
        List < StoreBasicInfoEntity > list = sRepo.findAll();
        List < StoreBasicInfoEntity > result = new ArrayList < StoreBasicInfoEntity > ();

        for (StoreBasicInfoEntity s: list) {
            if (s.getSbiBranchName().contains(branchName)) { // param으로 받은 branchName 조회한 뒤
                result.add(s); // 포함되면 result에 넣기
            }
        }

        // 해당 점포명이 없을 시
        if (result.isEmpty()) {
            resultMap.put("status", false);
            resultMap.put("message", "존재하지 않는 지점 명입니다.");
            return new ResponseEntity < > (resultMap, HttpStatus.BAD_REQUEST);
        }
        // 해당 점포명이 존재할 시
        else {
            resultMap.put("totalBranchStoreCount", result.size());
            resultMap.put("totalPage", (int) Math.ceil(result.size() / 10.0));
            resultMap.put("list", result);
            resultMap.put("status", true);
            resultMap.put("message", "[" + branchName + "] 키워드를 포함하는 지점들을 찾았습니다.");
            return new ResponseEntity < > (resultMap, HttpStatus.CREATED);
        }
    }
    @GetMapping("/member")
    public ResponseEntity < Object > searchMemberName(String memberName) {
        Map < String, Object > resultMap = new LinkedHashMap < String, Object > ();
        List < MemberEntity > list = mRepo.findAll();
        List < MemberEntity > result = new ArrayList < MemberEntity > ();

        for (MemberEntity s: list) {
            if (s.getMiName().contains(memberName)) { // param으로 받은 branchName 조회한 뒤
                result.add(s); // 포함되면 result에 넣기
            }
        }

        // 해당 점포명이 없을 시
        if (result.isEmpty()) {
            resultMap.put("status", false);
            resultMap.put("message", "존재하지 않는 회원 명입니다.");
            return new ResponseEntity < > (resultMap, HttpStatus.BAD_REQUEST);
        }
        // 해당 점포명이 존재할 시
        else {
            resultMap.put("totalMemberCount", result.size());
            resultMap.put("totalPage", (int) Math.ceil(result.size() / 10.0));
            resultMap.put("list", result);
            resultMap.put("status", true);
            resultMap.put("message", "[" + memberName + "] 키워드를 포함하는 회원들을 찾았습니다.");
            return new ResponseEntity < > (resultMap, HttpStatus.CREATED);
        }
    }
    @GetMapping("/notice")
    public ResponseEntity < Object > searchNoticeName(String noticeName) {
        Map < String, Object > resultMap = new LinkedHashMap < String, Object > ();
        List < AnnouncementEntity > list = aRepo.findAll();
        List < AnnouncementEntity > result = new ArrayList < AnnouncementEntity > ();

        for (AnnouncementEntity s: list) {
            if (s.getSaTitle().contains(noticeName)) { // param으로 받은 branchName 조회한 뒤
                result.add(s); // 포함되면 result에 넣기
            }
        }

        // 해당 점포명이 없을 시
        if (result.isEmpty()) {
            resultMap.put("status", false);
            resultMap.put("message", "존재하지 않는 공지사항입니다.");
            return new ResponseEntity < > (resultMap, HttpStatus.BAD_REQUEST);
        }
        // 해당 점포명이 존재할 시
        else {
            resultMap.put("totalNoticeCount", result.size());
            resultMap.put("totalPage", (int) Math.ceil(result.size() / 10.0));
            resultMap.put("list", result);
            resultMap.put("status", true);
            resultMap.put("message", "[" + noticeName + "] 키워드를 포함하는 공지사항들을 찾았습니다.");
            return new ResponseEntity < > (resultMap, HttpStatus.CREATED);
        }
    }
    @GetMapping("/coupon")
    public ResponseEntity < Object > searchCouponName(String couponName) {
        Map < String, Object > resultMap = new LinkedHashMap < String, Object > ();
        List < CouponEntity > list = cRepo.findAll();
        List < CouponEntity > result = new ArrayList < CouponEntity > ();

        for (CouponEntity s: list) {
            if (s.getCiName().contains(couponName)) { // param으로 받은 branchName 조회한 뒤
                result.add(s); // 포함되면 result에 넣기
            }
        }

        // 해당 점포명이 없을 시
        if (result.isEmpty()) {
            resultMap.put("status", false);
            resultMap.put("message", "존재하지 않는 쿠폰 입니다.");
            return new ResponseEntity < > (resultMap, HttpStatus.BAD_REQUEST);
        }
        // 해당 점포명이 존재할 시
        else {
            resultMap.put("totalCouponCount", result.size());
            resultMap.put("totalPage", (int) Math.ceil(result.size() / 10.0));
            resultMap.put("list", result);
            resultMap.put("status", true);
            resultMap.put("message", "[" + couponName + "] 키워드를 포함하는 쿠폰들을 찾았습니다.");
            return new ResponseEntity < > (resultMap, HttpStatus.CREATED);
        }
    }


}