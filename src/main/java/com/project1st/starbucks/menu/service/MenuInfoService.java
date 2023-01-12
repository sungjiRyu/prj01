package com.project1st.starbucks.menu.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project1st.starbucks.menu.entity.MenuBasicInfoEntity;
import com.project1st.starbucks.menu.repository.MenuBasicInfoRepository;
import com.project1st.starbucks.menu.repository.ProductCategoryRepository;

@Service
public class MenuInfoService {
    @Autowired MenuBasicInfoRepository mbiRepo;
    @Autowired ProductCategoryRepository pcRepo;
    @Autowired MenuBasicInfoRepository mRepo;
    
    
    // <전체 메뉴 조회하기> -> 완료 ♥
    public ResponseEntity<Object> menuList(Pageable pageable) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("list", mRepo.findAll());
        //페이징 처리
        Page<MenuBasicInfoEntity> page = mbiRepo.findAll(pageable);
        resultMap.put("totalPage", page.getTotalPages());
        resultMap.put("totalCount", page.getTotalElements());
        resultMap.put("currentPage", page.getNumber());
        resultMap.put("status", true);
        resultMap.put("message","전체 메뉴를 모두 조회합니다.");
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
    

    // <특정 메뉴 조회하기> -> 완료 ♥
    public ResponseEntity<Object> munuDetailList(Long mbiSeq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        //menu_info 테이블에 존재하지 않는 seq 걸러내기
        Optional<MenuBasicInfoEntity> menuOptional =  mbiRepo.findById(mbiSeq);
        if(menuOptional.isEmpty()){
            resultMap.put("status", false);
            resultMap.put("message", mbiSeq + "번 메뉴는 존재하지 않습니다.");
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        }
        
        // 조건 만족시 특정 메뉴 조회
        resultMap.put("detail", mRepo.findById(mbiSeq));
        resultMap.put("status", true);
        resultMap.put("message", mbiSeq + "번 메뉴를 조회했습니다.");
        return new ResponseEntity<>(resultMap, HttpStatus.CREATED);
    }
        

    // <메뉴 검색하기> -> 완료 ♥
    public ResponseEntity<Object> searchMenuName(String menuName) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        List<MenuBasicInfoEntity> list = mRepo.findAll();
        List<MenuBasicInfoEntity> result = new ArrayList<MenuBasicInfoEntity>();

        for(MenuBasicInfoEntity m : list) {
            if(m.getMbiName().contains(menuName)){ // param으로 받은 menuName으로 조회한 뒤
                result.add(m);  // 포함되면 result에 넣기
            }
        }

        // 메뉴가 없을시
        if(result.isEmpty()) {
            resultMap.put("status", false); 
            resultMap.put("message", "존재하지 않는 메뉴명입니다."); 
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        }
        // 메뉴가 존재할시
        else {
            resultMap.put("totalProductCount", result.size());
            resultMap.put("totalPage", (int)Math.ceil(result.size()/10.0));
            resultMap.put("list", result);
            resultMap.put("status", true); 
            resultMap.put("message", "[" + menuName + "] 라는 키워드를 포함하는 메뉴들을 조회했습니다."); 
            return new ResponseEntity<>(resultMap, HttpStatus.CREATED);
        }

    }

}
