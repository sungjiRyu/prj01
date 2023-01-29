package com.project1st.starbucks.menu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project1st.starbucks.menu.service.MenuInfoService;




@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired MenuInfoService mService;
    
    // @GetMapping("/list") // <전체 메뉴 조회> -> 완료 ♥
    @RequestMapping(path = "/list", method = RequestMethod.GET, produces = "application/hal+json;charset=utf8")
    public ResponseEntity<Object> getMenuList(Pageable pageable) {
        return mService.menuList(pageable);
    }

    @GetMapping("/list/detail") // <특정 메뉴 조회>
    public ResponseEntity<Object> getMunuDetailList(@RequestParam Long menuNo) {
        return mService.munuDetailList(menuNo);
    }

    @GetMapping("/search") // <메뉴 검색> -> 완료 ♥
    public ResponseEntity<Object> getMenuSearch(@RequestParam String menuName) {
        return mService.searchMenuName(menuName);
    }


}
