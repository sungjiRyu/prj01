package com.project1st.starbucks.menu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project1st.starbucks.menu.service.CategoryService;

@RestController
@RequestMapping("/cate")
public class CategoryController {
    @Autowired CategoryService cService;

    @GetMapping("/list") // <전체 카테고리 조회> -> 완료 ♥
    public ResponseEntity<Object> getCategoryList() {
        return cService.categoryList();
    }

    @GetMapping("/detail") // <상위 카테고리 선택시 해당 하위카테고리 조회> -> 완료 ♥
    public ResponseEntity<Object> getCategoryParentList(@RequestParam Long parentSeq) {
        return cService.categoryDetailList(parentSeq);
    }

    @GetMapping("/detail/menu") // <하위 카테고리 선택시 해당 메뉴들 조회> -> 완료 ♥
    public ResponseEntity<Object> getCategoryToMenuList(@RequestParam Long childSeq) {
        return cService.categoryToMenuList(childSeq);
    }

    
    @GetMapping("/searchmenu") // <상위 카테고리 내에서 검색하기> -> 완료 ♥
    public ResponseEntity<Object> getMenuSearch(@RequestParam Long parentSeq, @RequestParam String menuName) {
        return cService.searchMenuNameInCategory(parentSeq, menuName);
    }
    
}
