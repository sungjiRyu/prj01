package com.project1st.starbucks.store.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project1st.starbucks.store.service.StoreService;
import com.project1st.starbucks.store.vo.StoreEditVO;
import com.project1st.starbucks.store.vo.StoreMenuAddVO;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/store")
public class StoreController {
    @Autowired StoreService stService;

    @GetMapping("/mystore") // <내 지점 조회하기> -> 완료 ♥
    public ResponseEntity<Object> getMyStoreInfo(@RequestParam Long memberNo) {
        return stService.myStoreInfo(memberNo);
    }


    @PatchMapping("/mystore") // <내 지점 수정하기> -> 완료 ♥
    public ResponseEntity<Object> patchMyStoreEdit(@RequestBody StoreEditVO data, @RequestBody Long memberNo) {
        return stService.myStoreEdit(data, memberNo);
    }


    @GetMapping("/menu/search") // <내 지점에 메뉴 검색하기> 
    public ResponseEntity<Object> getStoreMenuSearch(@RequestParam String menuName, @RequestParam Long memberNo) {
        return stService.storeMenuSearch(menuName, memberNo);
    }


    @GetMapping("/menu/list") // <내 지점에 등록된 메뉴 보여주기> -> 완료 ♥
    public ResponseEntity<Object> getStoreMenuList(Pageable pageable, @RequestParam Long memberNo) {
        return stService.storeMenuList(pageable, memberNo);
    }


    @GetMapping("/menu/list/detail") // <내 지점에 등록된 메뉴 상세보기> -> 완료 ♥
    public ResponseEntity<Object> getStoreMenuDetail(@RequestParam Long memberNo, @RequestParam Long menuNo) {
        return stService.storeMenuDetail(memberNo, menuNo);
    }


    @PutMapping("/menu/add") // <내 지점에 메뉴 등록하기> -> 완료 ♥
    public ResponseEntity<Object> putStoreMenuList(@RequestParam Long menuNo, @RequestParam Long memberNo) {
        return stService.insertStoreMenuList(menuNo, memberNo);
    }

    
    @DeleteMapping("/menu/delete") // <내 지점에 메뉴 삭제하기> -> 완료 ♥
    public ResponseEntity< Map<String, Object> > deleteStoreMenuList(@RequestParam Long menuNo, @RequestParam Long memberNo) {
        return stService.deleteStoreMenuList(menuNo, memberNo);
    }


    @GetMapping("/search") // <가게 검색하기> -> 완료 ♥
    public ResponseEntity<Object> getStoreSearch(@RequestParam String storeName) {
        return stService.searchStoreBranchName(storeName);
    }



    @GetMapping("/cart/menu/list") // <주문하기 창에서 선택한 지점의 메뉴 전체 보기> -> 완료 ♥
    public ResponseEntity<Object> getCartStoreMenuList(@RequestParam Long storeSeq, Pageable pageable) {
        return stService.cartStoreMenuList(storeSeq, pageable);
    }


    @GetMapping("/cart/menu/list/detail") // <주문하기 창에서 선택한 지점의 메뉴 상세 보기> -> 완료 ♥
    public ResponseEntity<Object> getCartStoreMenuDetail(@RequestParam Long storeSeq, @RequestParam Long menuSeq) {
        return stService.cartStoreMenuDetail(storeSeq, menuSeq);
    }

}
