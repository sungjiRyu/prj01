package com.project1st.starbucks.store.service;

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

import com.project1st.starbucks.admin.entity.MemberEntity;
import com.project1st.starbucks.admin.entity.MenuImageEntity;
import com.project1st.starbucks.admin.entity.MenuNutritionEntity;
import com.project1st.starbucks.admin.repository.MemberInfoRepository;
import com.project1st.starbucks.admin.repository.MenuImageRepository;
import com.project1st.starbucks.admin.repository.MenuNutritionRepository;
import com.project1st.starbucks.menu.entity.MenuBasicInfoEntity;
import com.project1st.starbucks.menu.entity.MenuOptionInfoEntity;
import com.project1st.starbucks.menu.entity.MenuQrEntity;
import com.project1st.starbucks.menu.entity.ProductCateOptionCateConnectionEntity;
import com.project1st.starbucks.menu.entity.ProductCategoryEntity;
import com.project1st.starbucks.menu.repository.MenuBasicInfoRepository;
import com.project1st.starbucks.menu.repository.MenuOptionCategoryRepository;
import com.project1st.starbucks.menu.repository.MenuOptionInfoRepository;
import com.project1st.starbucks.menu.repository.MenuQrRepository;
import com.project1st.starbucks.menu.repository.ProductCateOptionCateConnectionRepository;
import com.project1st.starbucks.menu.repository.ProductCategoryRepository;
import com.project1st.starbucks.menu.vo.MenuDetailOptionListVO;
import com.project1st.starbucks.menu.vo.MenuDetailVO;
import com.project1st.starbucks.menu.vo.MenuImageVO;
import com.project1st.starbucks.menu.vo.MenuInfoVO;
import com.project1st.starbucks.menu.vo.MenuOptionListVO;
import com.project1st.starbucks.menu.vo.MenuOptionVO;
import com.project1st.starbucks.menu.vo.MenuStockVO;
import com.project1st.starbucks.menu.vo.ProductCategoryVO;
import com.project1st.starbucks.store.entity.StoreBasicInfoEntity;
import com.project1st.starbucks.store.entity.StoreMenuConnectEntity;
import com.project1st.starbucks.store.repository.StoreBasicInfoRepository;
import com.project1st.starbucks.store.repository.StoreMenuConnectRepository;
import com.project1st.starbucks.store.vo.StoreEditVO;
import com.project1st.starbucks.store.vo.StoreInfoVO;
import com.project1st.starbucks.store.vo.StoreMenuAddVO;
import com.project1st.starbucks.store.vo.StoreMenuVO;
import com.project1st.starbucks.store.vo.storeMenuStockVO;

import jakarta.servlet.http.HttpSession;

@Service
public class StoreService {
    @Autowired StoreBasicInfoRepository sRepo;
    @Autowired MenuBasicInfoRepository mRepo;
    @Autowired MemberInfoRepository memberRepo;
    @Autowired StoreMenuConnectRepository smRepo;
    @Autowired MenuOptionCategoryRepository optionCateRepo;
    @Autowired MenuOptionInfoRepository optionRepo;
    @Autowired MenuImageRepository miRepo;
    @Autowired MenuQrRepository menuQrRepo;
    @Autowired ProductCategoryRepository pcRepo;
    @Autowired ProductCateOptionCateConnectionRepository pcCateOptionCateRepo;
    @Autowired MenuNutritionRepository nutritionRepo;


    // <내 지점 조회하기> -> 완료 ♥
    public ResponseEntity<Object> myStoreInfo(HttpSession session) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        // 세션으로 로그인 정보 불러오기
        MemberEntity findStore = (MemberEntity)session.getAttribute("loginUser");
        if (findStore == null) {
            resultMap.put("status", false);
            resultMap.put("message", "내 지점 조회를 위해 점주 로그인을 해주세요.");
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        }
        // 로그인 정보가 점주인가 아닌가
        else if(findStore.getMiGroup() != 2){
            resultMap.put("status", false);
            resultMap.put("message", "점주 회원이 아닙니다.");
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        } 
        // 점주라면 해당 지점보여주기
        else {
            Long storeSeq = findStore.getMiSbiSeq();

            resultMap.put("status", true);
            resultMap.put("message", findStore.getMiName() + " 점주님의 지점입니다.");
            resultMap.put("list", new StoreInfoVO(sRepo.findById(storeSeq).get()));
    
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        }
    }
    

    // <내 지점 수정하기> -> 완료 ♥
    public ResponseEntity<Object> myStoreEdit(StoreEditVO storeInfoEdit, HttpSession session) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        // 세션으로 로그인 정보 불러오기
        MemberEntity findStore = (MemberEntity)session.getAttribute("loginUser");
        if (findStore == null) {
            resultMap.put("status", false);
            resultMap.put("message", "내 지점 수정을 위해 점주 로그인을 해주세요.");
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        }
        // 로그인 정보가 점주인가 아닌가
        else if(findStore.getMiGroup() != 2){
            resultMap.put("status", false);
            resultMap.put("message", "점주 회원이 아닙니다.");
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        } 
        // 점주라면 해당 지점 수정하기
        else {
            Long storeSeq = findStore.getMiSbiSeq();

            StoreBasicInfoEntity storeInfo = sRepo.findById(storeSeq).get();
            
            if (storeInfo != null) {
                if (storeInfoEdit.getStoreOpenTime() != null) {
                    storeInfo.setSbiOpenTime(storeInfoEdit.getStoreOpenTime());
                }
                if (storeInfoEdit.getStoreCloseTime() != null) {
                    storeInfo.setSbiCloseTime(storeInfoEdit.getStoreCloseTime());
                }
                if (storeInfoEdit.getStoreCloseDay() != null) {
                    storeInfo.setSbiCloseDay(storeInfoEdit.getStoreCloseDay());
                }
                if (storeInfoEdit.getStoreMinOrder() != null) {
                    storeInfo.setSbiMinOrder(storeInfoEdit.getStoreMinOrder());
                }
                if (storeInfoEdit.getStorePhone() != null) {
                    storeInfo.setSbiPhone(storeInfoEdit.getStorePhone());
                }
                if (storeInfoEdit.getStoreMinDeliveryTime() != null) {
                    storeInfo.setSbiMinDeliveryTime(storeInfoEdit.getStoreMinDeliveryTime());
                }
                if (storeInfoEdit.getStoreMaxDeliveryTime() != null) {
                    storeInfo.setSbiMaxDeliveryTime(storeInfoEdit.getStoreMaxDeliveryTime());
                }
                sRepo.save(storeInfo);
                resultMap.put("status", true);
                resultMap.put("message", "지점 정보가 수정되었습니다.");
            }
            return new ResponseEntity<>(resultMap, HttpStatus.OK);

        }
    }



    // <내 지점에 등록된 메뉴 검색하기> -> 완료 ♥
    public ResponseEntity<Object> storeMenuSearch(String menuName, HttpSession session) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        // 세션으로 로그인 정보 불러오기
        MemberEntity findStore = (MemberEntity)session.getAttribute("loginUser");
        if (findStore == null) {
            resultMap.put("status", false);
            resultMap.put("message", "내 지점 메뉴 검색을 위해 점주 로그인을 해주세요.");
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        }
        // 로그인 정보가 점주인가 아닌가
        else if(findStore.getMiGroup() != 2){
            resultMap.put("status", false);
            resultMap.put("message", "점주 회원이 아닙니다.");
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        } 
        // 점주라면 지점 내 메뉴 검색하기
        else {
            Long storeSeq = findStore.getMiSbiSeq();

            List<StoreMenuConnectEntity> list = smRepo.findAll();
            List<MenuImageVO> result = new ArrayList<MenuImageVO>();
            StoreInfoVO store = null;
            for(StoreMenuConnectEntity m : list) {
                if(m.getStore().getSbiSeq() == storeSeq && m.getMenu().getMbiName().contains(menuName)){ // param으로 받은 menuName으로 조회한 뒤
                    store = new StoreInfoVO(m.getStore());
                    result.add(new MenuImageVO(miRepo.findByMiiNumber(m.getMenu())));  // 포함되면 result에 넣기
                }
            }

            // 메뉴가 없을시
            if(result.isEmpty()) {
                resultMap.put("status", false); 
                resultMap.put("message", sRepo.findById(storeSeq).get().getSbiBranchName() + "에 [" + menuName + "] (이)라는 키워드를 포함하는 메뉴는 존재하지 않습니다."); 
                return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
            }
            // 메뉴가 존재할시
            else {
                resultMap.put("totalProductCount", result.size());
                resultMap.put("totalPage", (int)Math.ceil(result.size()/10.0));
                resultMap.put("list", new StoreMenuVO(store, result));
                resultMap.put("status", true); 
                resultMap.put("message", "[" + menuName + "] (이)라는 키워드를 포함하는 메뉴들을 조회했습니다."); 
                return new ResponseEntity<>(resultMap, HttpStatus.CREATED);
            }
        }
    }


    
    // <내 지점에 등록된 메뉴 보여주기>
    public ResponseEntity<Object> storeMenuList(Pageable pageable, HttpSession session) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        // 세션으로 로그인 정보 불러오기
        MemberEntity findStore = (MemberEntity)session.getAttribute("loginUser");
        if (findStore == null) {
            resultMap.put("status", false);
            resultMap.put("message", "내 지점 메뉴 전체조회를 위해 점주 로그인을 해주세요.");
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        }
        // 로그인 정보가 점주인가 아닌가
        else if(findStore.getMiGroup() != 2){
            resultMap.put("status", false);
            resultMap.put("message", "점주 회원이 아닙니다.");
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        } 
        // 점주라면 해당 지점 + 지점메뉴 보여주기
        else {
            Long storeSeq = findStore.getMiSbiSeq();

    
            // 가게 메뉴 보여주기        
            resultMap.put("list", smRepo.findAll());
            List<StoreMenuConnectEntity> list = smRepo.findByStore(sRepo.findById(storeSeq).get(), pageable).getContent();
            StoreInfoVO store = null;
            List<MenuImageVO> menuList = new ArrayList<MenuImageVO>();
            for(StoreMenuConnectEntity s : list) {
                store = new StoreInfoVO(s.getStore());
                menuList.add(new MenuImageVO(miRepo.findByMiiNumber(s.getMenu())));
            }
            resultMap.put("status", true);
            resultMap.put("message", store.getBranch() + "의 전체 메뉴 입니다.");
            resultMap.put("list", new StoreMenuVO(store, menuList));
    

            //페이징 처리
            Page<StoreMenuConnectEntity> page = smRepo.findAll(pageable);
            resultMap.put("totalPage", page.getTotalPages());
            resultMap.put("MenuTotalCount", (page.getTotalElements()) -2);
            resultMap.put("currentPage", page.getNumber());
    
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        }
            
    }
    


    // <내 지점에 특정 메뉴 상세보기> -> 완료 ♥
    public ResponseEntity<Object> storeMenuDetail(HttpSession session, Long menuNo) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();


        // 세션으로 로그인 정보 불러오기
        MemberEntity findStore = (MemberEntity)session.getAttribute("loginUser");
        if (findStore == null) {
            resultMap.put("status", false);
            resultMap.put("message", "내 지점 메뉴 상세조회를 위해 점주 로그인을 해주세요.");
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        }
        // 로그인 정보가 점주인가 아닌가
        else if(findStore.getMiGroup() != 2){
            resultMap.put("status", false);
            resultMap.put("message", "점주 회원이 아닙니다.");
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        } 

        // 점주라면 해당 지점 + 지점 상세 메뉴 보여주기
        else {
            Long storeSeq = findStore.getMiSbiSeq();

            // 메뉴 기존 테이블에 존재하지 않는 메뉴 검사
            Optional<StoreBasicInfoEntity> storeOptional =  sRepo.findById(storeSeq);
            Optional<MenuBasicInfoEntity> menuOptional =  mRepo.findById(menuNo);
            
            if(menuOptional.isEmpty()){
                resultMap.put("status", false);
                resultMap.put("message", "존재하지 않는 메뉴입니다.");
                return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
            }
    
            // 연결테이블에 존재하지 않는 seq 검사
            StoreMenuConnectEntity smEntity = smRepo.findByStoreAndMenu(storeOptional.get(), menuOptional.get());
            if((smEntity == null) || (smEntity.getStore().getSbiSeq() == storeSeq && smEntity.getMenu().getMbiSeq() != menuNo)){
                resultMap.put("status", false);
                resultMap.put("message", sRepo.findById(storeSeq).get().getSbiBranchName() + "에는 " + mRepo.findById(menuNo).get().getMbiName() + " 메뉴가 존재하지 않습니다.");
                return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
            }

            // 메뉴+옵션들이 표시되는 list 만들었고,,
            MenuDetailOptionListVO optionData = new MenuDetailOptionListVO();

            // 메뉴 상세를 optionData에 담기
            MenuImageEntity menuImage = miRepo.findByMiiNumber(mRepo.findById(menuNo).get());
            MenuQrEntity qr = menuQrRepo.findByMqiMbiSeq(menuNo);
            MenuNutritionEntity nutrition = nutritionRepo.findByMnMbiSeq(menuNo);
            MenuDetailVO result = new MenuDetailVO(menuImage, qr, nutrition);
            optionData.setDetail(result);

            // 메뉴 연결외래키로 메뉴카테고리 받아오고, 카테고리-옵션카테고리에서 옵션카테고리 받아오기
            ProductCategoryEntity menuCategory = pcRepo.findById(result.getMenuCategorySeq()).get();
            List<ProductCateOptionCateConnectionEntity> pcCateOptionCateConnect = pcCateOptionCateRepo.findByProductCategory(menuCategory);
            List<MenuOptionListVO> optionAllList = new ArrayList<MenuOptionListVO>();

            for(ProductCateOptionCateConnectionEntity p : pcCateOptionCateConnect){
                MenuOptionListVO optionAll = new MenuOptionListVO();
                optionAll.setOptionCategoryName(p.getMenuOpionCategory().getMocName());

                List<MenuOptionInfoEntity> optionList = optionRepo.findAllByMoiMocSeq(p.getMenuOpionCategory().getMocSeq());
                List<MenuOptionVO> options = new ArrayList<MenuOptionVO>();
                for(MenuOptionInfoEntity m : optionList) {
                    MenuOptionVO vo = new MenuOptionVO();
                    vo.setOptionName(m.getMoiName());
                    vo.setOptionCost(m.getMoiCost());
                    options.add(vo);
                }

                // 이거 리스트로 받기
                optionAll.setOption(options);
                optionAllList.add(optionAll);
            }
            optionData.setOptions(optionAllList);

            resultMap.put("status", true);
            resultMap.put("message", smEntity.getStore().getSbiBranchName() + "의 " + smEntity.getMenu().getMbiName() + " 메뉴를 소개합니다.");
            resultMap.put("MenuDetail", optionData);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);

        }
    }
    


     // <내 지점에 메뉴 등록하기> -> 완료 ♥ -> vo 써야하나?
    public ResponseEntity<Object> insertStoreMenuList(Long menuNo, HttpSession session) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();


        // 세션으로 로그인 정보 불러오기
        MemberEntity findStore = (MemberEntity)session.getAttribute("loginUser");
        if (findStore == null) {
            resultMap.put("status", false);
            resultMap.put("message", "내 지점 메뉴 등록을 위해 점주 로그인을 해주세요.");
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        }
        // 로그인 정보가 점주인가 아닌가
        else if(findStore.getMiGroup() != 2){
            resultMap.put("status", false);
            resultMap.put("message", "점주 회원이 아닙니다.");
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        } 
        // 점주라면 해당 지점 + 지점 메뉴 추가하기
        else {
            Long storeSeq = findStore.getMiSbiSeq();

            
            Optional<StoreBasicInfoEntity> store =  sRepo.findById(storeSeq);
            Optional<MenuBasicInfoEntity> menu =  mRepo.findById(menuNo);
            
            
            if(menu.isEmpty()){
                resultMap.put("status", false);
                resultMap.put("message", "존재하지 않는 메뉴 추가 입니다.");
                return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
            }
            
            
            // 중복검사 (연결테이블 내)
            StoreMenuConnectEntity smEntity = smRepo.findByStoreAndMenu(store.get(), menu.get());
            if((smEntity != null) && ((smEntity.getStore().getSbiSeq() == storeSeq) && (smEntity.getMenu().getMbiSeq() == menuNo))){
                resultMap.put("status", false);
                resultMap.put("message", smEntity.getStore().getSbiBranchName() + "에 " + smEntity.getMenu().getMbiName() + "번 메뉴는 이미 존재합니다.");
                return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
            }
    
            // 조건 만족시 메뉴 입력
            StoreMenuConnectEntity connEntity = StoreMenuConnectEntity.builder()
                .store(sRepo.findById(storeSeq).get())
                .menu(mRepo.findById(menuNo).get()).build();
            smRepo.save(connEntity);
            resultMap.put("status", true);
            resultMap.put("message", connEntity.getStore().getSbiBranchName() + "번 가게에 " + connEntity.getMenu().getMbiName() + "번 메뉴 등록이 완료되었습니다.");
            resultMap.put("inserted_data", connEntity);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
            
        }
        
    }



    // <내 지점에 등록된 메뉴 삭제하기> -> 완료 ♥
    public ResponseEntity< Map<String, Object> > deleteStoreMenuList(Long menuNo, HttpSession session) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();


        // 세션으로 로그인 정보 불러오기
        MemberEntity findStore = (MemberEntity)session.getAttribute("loginUser");
        if (findStore == null) {
            resultMap.put("status", false);
            resultMap.put("message", "내 지점 메뉴 삭제를 위해 점주 로그인을 해주세요.");
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        }
        // 로그인 정보가 점주인가 아닌가
        else if(findStore.getMiGroup() != 2){
            resultMap.put("status", false);
            resultMap.put("message", "점주 회원이 아닙니다.");
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        } 
        // 점주라면 해당 지점 + 지점 메뉴 삭제하기
        else {
            Long storeSeq = findStore.getMiSbiSeq();
            
            Optional<StoreBasicInfoEntity> store =  sRepo.findById(storeSeq);
            Optional<MenuBasicInfoEntity> menu =  mRepo.findById(menuNo);

            // 애초에 존재하지 않는 메뉴
            if(menu.isEmpty()){
                resultMap.put("status", false);
                resultMap.put("message", "존재하지 않는 메뉴 번호입니다.");
                return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
            }

            // 지점에 존재하는 메뉴 삭제
            StoreMenuConnectEntity smEntity = smRepo.findByStoreAndMenu(store.get(), menu.get());
            if(smEntity!= null){
                smRepo.delete(smEntity);
                resultMap.put("status", true);
                resultMap.put("message", store.get().getSbiBranchName() + "의 " + menu.get().getMbiName() + " 메뉴가 삭제되었습니다.");
                return new ResponseEntity<>(resultMap, HttpStatus.OK);
            }
            else{ // 존재하지 않는 seq 입력시 
                resultMap.put("status", false);
                resultMap.put("message", "이미 " + store.get().getSbiBranchName() + "에 존재하지 않는 메뉴입니다.");
                return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
            }
        }

    }


    
    // <지점명으로 가게 찾기> -> 완료 ♥
    public ResponseEntity<Object> searchStoreBranchName(String storeName) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        List<StoreBasicInfoEntity> list = sRepo.findAll();
        List<StoreInfoVO> result = new ArrayList<StoreInfoVO>();

        for(StoreBasicInfoEntity s : list) {
            if(s.getSbiBranchName().contains(storeName)){ // param으로 받은 branchName 조회한 뒤
                StoreInfoVO m = new StoreInfoVO(s);
                result.add(m);
            }
        }

        // 해당 점포명이 없을 시
        if(result.isEmpty()) {
            resultMap.put("status", false); 
            resultMap.put("message", "존재하지 않는 지점 명입니다."); 
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        }
        // 해당 점포명이 존재할 시
        else {
            resultMap.put("totalBranchStoreCount", result.size());
            resultMap.put("totalPage", (int)Math.ceil(result.size()/10.0));
            resultMap.put("list", result);
            resultMap.put("status", true); 
            resultMap.put("message", "[" + storeName + "] 키워드를 포함하는 지점들을 찾았습니다."); 
            return new ResponseEntity<>(resultMap, HttpStatus.CREATED);
        }
    }
    


    // <주문하기 창에서 선택한 지점의 메뉴 전체 보기>
    public ResponseEntity<Object> cartStoreMenuList(Long storeSeq, Pageable pageable) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        //존재하지 않는 가게 번호 시퀀스 처리
        if(sRepo.findById(storeSeq).isEmpty()){
            resultMap.put("status", false);
            resultMap.put("message", "존재하지 않는 가게 번호입니다.");
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        }

        // 가게 전체 메뉴 보여주기        
        resultMap.put("list", smRepo.findAll());
        List<StoreMenuConnectEntity> list = smRepo.findByStore(sRepo.findById(storeSeq).get(), pageable).getContent();
        StoreInfoVO store = null;
        List<MenuStockVO> menuList = new ArrayList<MenuStockVO>();
        for(StoreMenuConnectEntity s : list) {
            store = new StoreInfoVO(s.getStore());
            menuList.add(new MenuStockVO(smRepo.findById(s.getSmcSeq()).get()));
        }
        resultMap.put("status", true);
        resultMap.put("message", store.getBranch() + "의 전체 메뉴 입니다.");
        resultMap.put("list", new storeMenuStockVO(store, menuList));

        //페이징 처리
        Page<StoreMenuConnectEntity> page = smRepo.findAll(pageable);
        resultMap.put("totalPage", page.getTotalPages());
        resultMap.put("MenuTotalCount", (page.getTotalElements()) -2);
        resultMap.put("currentPage", page.getNumber());

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
            
    }
    


    // <주문하기 창에서 선택한 지점의 메뉴 상세 보기> -> 완료 ♥
    public ResponseEntity<Object> cartStoreMenuDetail(Long storeSeq, Long menuSeq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        
        // 메뉴 기존 테이블에 존재하지 않는 메뉴 검사
        Optional<StoreBasicInfoEntity> storeOptional =  sRepo.findById(storeSeq);
        Optional<MenuBasicInfoEntity> menuOptional =  mRepo.findById(menuSeq);
        if(menuOptional.isEmpty()){
            resultMap.put("status", false);
            resultMap.put("message", "존재하지 않는 메뉴입니다.");
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        }

        // 연결테이블에 존재하지 않는 seq 검사
        StoreMenuConnectEntity smEntity = smRepo.findByStoreAndMenu(storeOptional.get(), menuOptional.get());
        if((smEntity == null) || (smEntity.getStore().getSbiSeq() == storeSeq && smEntity.getMenu().getMbiSeq() != menuSeq)){
            resultMap.put("status", false);
            resultMap.put("message", sRepo.findById(storeSeq).get().getSbiBranchName() + "에는 " + mRepo.findById(menuSeq).get().getMbiName() + " 메뉴가 존재하지 않습니다.");
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        }


        // 메뉴+옵션들이 표시되는 list 만들었고,,
        MenuDetailOptionListVO optionData = new MenuDetailOptionListVO();

        // 메뉴 상세를 optionData에 담기
        MenuImageEntity menuImage = miRepo.findByMiiNumber(mRepo.findById(menuSeq).get());
        MenuQrEntity qr = menuQrRepo.findByMqiMbiSeq(menuSeq);
        MenuNutritionEntity nutrition = nutritionRepo.findByMnMbiSeq(menuSeq);
        MenuDetailVO result = new MenuDetailVO(menuImage, qr, nutrition);
        optionData.setDetail(result);

        // 메뉴 연결외래키로 메뉴카테고리 받아오고, 카테고리-옵션카테고리에서 옵션카테고리 받아오기
        ProductCategoryEntity menuCategory = pcRepo.findById(result.getMenuCategorySeq()).get();
        
        List<ProductCateOptionCateConnectionEntity> pcCateOptionCateConnect = pcCateOptionCateRepo.findByProductCategory(menuCategory);
        List<MenuOptionListVO> optionAllList = new ArrayList<MenuOptionListVO>();

        for(ProductCateOptionCateConnectionEntity p : pcCateOptionCateConnect){
            MenuOptionListVO optionAll = new MenuOptionListVO();
            optionAll.setOptionCategoryName(p.getMenuOpionCategory().getMocName());
            
            List<MenuOptionInfoEntity> optionList = optionRepo.findAllByMoiMocSeq(p.getMenuOpionCategory().getMocSeq());
            List<MenuOptionVO> options = new ArrayList<MenuOptionVO>();
            for(MenuOptionInfoEntity m : optionList) {
                MenuOptionVO vo = new MenuOptionVO();
                vo.setOptionName(m.getMoiName());
                vo.setOptionCost(m.getMoiCost());
                options.add(vo);
            }

            // 이거 리스트로 받기
            optionAll.setOption(options);
            optionAllList.add(optionAll);
        }
        optionData.setOptions(optionAllList);
            
        resultMap.put("status", true);
        resultMap.put("message", smEntity.getStore().getSbiBranchName() + "의 " + smEntity.getMenu().getMbiName() + " 메뉴를 소개합니다.");
        resultMap.put("MenuDetail", optionData);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
        
    }
    


}
