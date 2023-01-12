package com.project1st.starbucks.store.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project1st.starbucks.store.service.StoreService;
import com.project1st.starbucks.store.vo.StoreMenuAddVO;

@Controller
@RequestMapping("/store")
public class StoreController {
    @Autowired StoreService stService;

    @GetMapping("/menu/list") // <가게에 등록된 메뉴 보여주기>  // 점주 로그인 상태에서 본인의 가게에만 설정할 수 있도록
    public ResponseEntity<Object> getStoreMenuList(@RequestParam Long storeSeq, Pageable pageable) {
        return stService.storeMenuList(pageable, storeSeq);
    }


    @GetMapping("/menu/list/detail") // <가게에 등록된 메뉴 상세보기>  // 점주 로그인 상태에서 본인의 가게에만 설정할 수 있도록
    public ResponseEntity<Object> getStoreMenuDetail(@RequestParam Long storeSeq, @RequestParam Long menuSeq) {
        return stService.storeMenuDetail(storeSeq, menuSeq);
    }


    @PutMapping("/menu/add") // <가게에 메뉴 등록하기>  // 점주 로그인 상태에서 본인의 가게에만 설정할 수 있도록
    public ResponseEntity<Object> putStoreMenuList(@RequestBody StoreMenuAddVO data) {
        return stService.insertStoreMenuList(data);
    }

    
    @DeleteMapping("/menu/delete") // <가게에 메뉴 삭제하기>  // 점주 로그인 상태에서 본인의 가게에만 설정할 수 있도록
    public ResponseEntity< Map<String, Object> > deleteStoreMenuList(@RequestParam Long storeSeq, @RequestParam Long menuSeq) {
        return stService.deleteStoreMenuList(storeSeq, menuSeq);
    }


    @GetMapping("/search") // <가게 검색하기> -> 완료 ♥
    public ResponseEntity<Object> getStoreSearch(@RequestParam String branchName) {
        return stService.searchStoreBranchName(branchName);
    }
}
