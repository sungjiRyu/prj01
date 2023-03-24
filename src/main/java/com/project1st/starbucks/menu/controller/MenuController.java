package com.project1st.starbucks.menu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project1st.starbucks.menu.service.MenuInfoService;

import jakarta.servlet.http.HttpServletRequest;




@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired MenuInfoService mService;
    
    // @GetMapping("/list") // <전체 메뉴 조회> -> 완료 ♥
    @RequestMapping(path = "/list", method = RequestMethod.GET, produces = "application/hal+json;charset=utf8")
    public ResponseEntity<Object> getMenuList(Pageable pageable) {
        return mService.menuList(pageable);
    }
    
    // @GetMapping("/list/detail") // <특정 메뉴 조회>
    @RequestMapping(path = "/list/detail", method = RequestMethod.GET, produces = "application/hal+json;charset=utf8")
    public ResponseEntity<Object> getMunuDetailList(@RequestParam Long menuNo) {
        return mService.munuDetailList(menuNo);
    }

    @GetMapping("/search") // <메뉴 검색> -> 완료 ♥
    public ResponseEntity<Object> getMenuSearch(@RequestParam String menuName) {
        return mService.searchMenuName(menuName);
    }

    // <QR코드 생성>
    @PostMapping("/qr/new")
    public ResponseEntity<Object> postQR(@RequestParam String menuName, @RequestParam Long menuNo) throws Exception{
        return mService.makeQR(menuName, menuNo);
    }
    // public ResponseEntity<Object> postQR(@RequestParam String menuName) throws Exception{
    //     return mService.makeQR(menuName);
    // }


    // <만든 QR코드 이미지 다운로드>
    @GetMapping("/qr/image/{uri}")
    public ResponseEntity<Resource> getMenuQRImage(@PathVariable String uri, HttpServletRequest request) throws Exception {
        return mService.getMenuQRImage(uri, request);
    }


}
