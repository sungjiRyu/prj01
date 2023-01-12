package com.project1st.starbucks.menu.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project1st.starbucks.menu.entity.ProductCategoryEntity;
import com.project1st.starbucks.menu.repository.MenuBasicInfoRepository;
import com.project1st.starbucks.menu.repository.ProductCategoryRepository;
import com.project1st.starbucks.menu.vo.ProductCategoryListVO;

@Service
public class CategoryService {
    @Autowired ProductCategoryRepository pcRepo;
    @Autowired MenuBasicInfoRepository mRepo;

    // <전체 카테고리 조회하기> -> 완료 ♥
    public ResponseEntity<Object> categoryList() {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<ProductCategoryEntity> list = pcRepo.findByPcParentSeqIsNull(); // Parent가 NULL인(최상위 카테고리) 조회
        List<ProductCategoryListVO> cateList = new ArrayList<ProductCategoryListVO>();
        for(ProductCategoryEntity e : list) { // 최상위 카테고리 순차조회
            ProductCategoryListVO data = new ProductCategoryListVO(); // VO 생성
            data.setCategoryName(e.getPcName()); // 최상위 카테고리 이름 설정
             // 최상위 카테고리의 번호를 부모 카테고리 번호로 설정된 카테고리 목록 가져오기
            List<ProductCategoryEntity> childList = pcRepo.findByPcParentSeq(e.getPcSeq());
            List<String> childNameList = new ArrayList<String>(); // 이름 목록 생성
            for(ProductCategoryEntity child : childList) { // 차일드 리스트 순차조회
                childNameList.add(child.getPcName()); // 이름 추출후 리스트에 등록
            }
            data.setChildCategoryName(childNameList); // VO에 childList 설정
            cateList.add(data); // 리스트에 만들어진 VO 데이터 추가
        }
        resultMap.put("status", true);
        resultMap.put("message","전체 카테고리를 모두 조회합니다.");
        resultMap.put("list", cateList);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
        

    // <특정 부모카테고리 조회시 상세 카테고리 조회하기> -> 완료 ♥
    public ResponseEntity<Object> categoryDetailList(Long pcSeq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        // product_category 테이블에 존재하지 않는 seq 걸러내기
        if(pcRepo.findByPcParentSeq(pcSeq).isEmpty()) {
            resultMap.put("status", false);
            resultMap.put("message", "존재하지 않는 상위 카테고리 번호입니다.");
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        }
        
        // 상위카테고리 조회시 하위카테고리 보여주기
        resultMap.put("status", true);
        resultMap.put("message", "상위 카테고리 번호 " + pcSeq + "번의 하위 카테고리를 소개합니다.");
        resultMap.put("list", pcRepo.findByPcParentSeq(pcSeq));
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
        
    }

    
    // <특정 상세카테고리 조회시 메뉴 나오게하기> -> 완료 ♥
    public ResponseEntity<Object> categoryToMenuList(Long pcSeq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        // 하위카테고리 번호가 아닌거 걸러내기 (부모 카테고리)
        if(!pcRepo.findByPcParentSeq(pcSeq).isEmpty()) {
            resultMap.put("status", false);
            resultMap.put("message", "상위카테고리 번호입니다. 하위카테고리 번호를 입력하세요.");
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        }
        // 하위카테고리 번호가 아닌거 걸러내기 (존재하지 않는 카테고리)
        if(pcRepo.findById(pcSeq).isEmpty()) {
            resultMap.put("status", false);
            resultMap.put("message", "존재하지 않는 하위 카테고리 번호입니다.");
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        }
        
        // 상위카테고리 조회시 하위카테고리 보여주기
        resultMap.put("status", true);
        resultMap.put("message", "하위 카테고리 번호 " + pcSeq + "번의 메뉴들을 소개합니다.");
        resultMap.put("list", mRepo.findByMbiPcSeq(pcSeq));
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }



}
