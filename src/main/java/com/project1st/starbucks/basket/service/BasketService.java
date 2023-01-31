package com.project1st.starbucks.basket.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project1st.starbucks.admin.entity.MemberEntity;
import com.project1st.starbucks.basket.entity.ShoppingBasketEntity;
import com.project1st.starbucks.basket.entity.ShoppingBasketOptionEntity;
import com.project1st.starbucks.basket.repository.ShoppingBasketAddRepository;
import com.project1st.starbucks.basket.repository.ShoppingBasketOptionRepository;
import com.project1st.starbucks.basket.repository.ShoppingBasketRepository;
import com.project1st.starbucks.basket.vo.ChangeBasketVO;
import com.project1st.starbucks.basket.vo.DeleteBasketVO;
import com.project1st.starbucks.basket.vo.DetailPageBasketVO;
import com.project1st.starbucks.basket.vo.ShoppingBasketOptionVO;
import com.project1st.starbucks.menu.entity.MenuOptionInfoEntity;
import com.project1st.starbucks.menu.repository.MenuOptionInfoRepository;
import com.project1st.starbucks.store.entity.StoreMenuConnectEntity;
import com.project1st.starbucks.store.repository.StoreMenuConnectRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class BasketService {
    @Autowired ShoppingBasketRepository sbRepo;
    @Autowired ShoppingBasketOptionRepository sbopRepo;
    @Autowired StoreMenuConnectRepository smcRepo;
    @Autowired ShoppingBasketAddRepository sbAddRepo;

    // public ResponseEntity<Object> addDetailBasket(DetailBasketVO data, HttpSession session) {       
    //     MemberEntity memberInfo = (MemberEntity)session.getAttribute("loginUser");
    //     Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

    //     if(memberInfo == null) {
    //         resultMap.put("result", false);
    //         return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
    //     }

    //     long e = 1;
    //     Optional<StoreMenuConnectEntity> storeMenuConnect = smcRepo.findById(data.getSbSmcSeq());
    //     ShoppingBasketEntity sbEntity = sbRepo.findBySbMiSeqAndStoreMenuConnectAndSbStatus(memberInfo.getMiSeq(), storeMenuConnect.get(), e);        

    //     if(sbEntity != null) {
    //         sbEntity.setSbNumber(sbEntity.getSbNumber() + 1);
    //         sbRepo.save(sbEntity);
    //         resultMap.put("result", true);
    //         resultMap.put("message", "수량추가");
    //     }

    //     else { long i = 1;        
    //     ShoppingBasketEntity entity = ShoppingBasketEntity.builder().sbMiSeq(memberInfo.getMiSeq()).sbStatus(i).sbNumber(data.getSbNumber())
    //                                             .storeMenuConnect(smcRepo.findById(data.getSbSmcSeq()).get()).build();
    //     sbRepo.save(entity);
    //     resultMap.put("result", true);
    //     resultMap.put("message", "장바구니 추가 완료");
    //     resultMap.put("entity", entity);        
    //     }

    //     return new ResponseEntity<>(resultMap, HttpStatus.OK);
    // }
    @Autowired MenuOptionInfoRepository menuOptionInfoRepo;

    public ResponseEntity<Object> addDetailBasket(DetailPageBasketVO data, HttpSession session) {   

        MemberEntity memberInfo = (MemberEntity)session.getAttribute("loginUser");

        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        if(memberInfo == null) {
            resultMap.put("result", false);
            resultMap.put("message", "로그인 해주세요");
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        }

        else {             
            // long e = 1;
            // Optional<StoreMenuConnectEntity> storeMenuConnect = smcRepo.findById(data.getShoppingBasketVo().getSbSmcSeq());
            // // ShoppingBasketEntity sbEntity = sbRepo.findBySbMiSeqAndStoreMenuConnectAndSbStatus(memberInfo.getMiSeq(), storeMenuConnect.get(), e);
            
            // // List<ShoppingBasketOptionEntity> sbOptionEntity = sbopRepo.findByShoppingBasket(sbEntity);


            // if(sbEntity != null && sbAddRepo.findBySboOptionOrderNumber(sbEntity.getSbOrderNumber()) != null) {
            //     sbEntity.setSbNumber(sbEntity.getSbNumber() + 1);
            //     resultMap.put("result", true);
            //     resultMap.put("message", "수량추가");
            //     return new ResponseEntity<>(resultMap, HttpStatus.OK);
            // }

            // else {
            Integer orderNo = (int)(Math.random()*10000000);


                if(data.getShoppingBasketOption() == null) {
                    long i = 1;        
                    ShoppingBasketEntity entity = ShoppingBasketEntity.builder().sbMiSeq(memberInfo.getMiSeq()).sbStatus(i).sbNumber(data.getShoppingBasketVo().getSbNumber())
                                                        .sbOrderNumber(orderNo)
                                                            .storeMenuConnect(smcRepo.findById(data.getShoppingBasketVo().getSbSmcSeq()).get()).build();
                    sbRepo.save(entity);
                    resultMap.put("result", true);
                    resultMap.put("message", "장바구니 추가 완료");
                    // resultMap.put("entity", entity);  
                    return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
                }
                
                long i = 1;        
                ShoppingBasketEntity entity = ShoppingBasketEntity.builder().sbMiSeq(memberInfo.getMiSeq()).sbStatus(i).sbNumber(data.getShoppingBasketVo().getSbNumber())
                                                .sbOrderNumber(orderNo)
                                                .storeMenuConnect(smcRepo.findById(data.getShoppingBasketVo().getSbSmcSeq()).get()).build();
                sbRepo.save(entity);
                resultMap.put("result", true);
                resultMap.put("message", "장바구니 추가 완료");
                // resultMap.put("entity", entity);  

                for(ShoppingBasketOptionVO opt : data.getShoppingBasketOption()) {
                    MenuOptionInfoEntity menuOptionEntity = menuOptionInfoRepo.findByMoiSeq(opt.getSboMoiSeq());
                    ShoppingBasketOptionEntity optionEntity =ShoppingBasketOptionEntity.builder().sboNumber(opt.getSboNumber())
                                                            .sboOptionOrderNumber(entity.getSbOrderNumber())
                                                            .menuOption(menuOptionEntity).shoppingBasket(entity).build();        

                    sbopRepo.save(optionEntity);  
                    resultMap.put("optionMessage", "옵션추가 완료");
                }
            }
        // }   
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }




    // public ResponseEntity<Object> deleteBasket(DeleteBasketVO data, HttpSession session) {
    //     MemberEntity memberInfo = (MemberEntity)session.getAttribute("loginUser");  

    //     Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

    //     if(memberInfo == null) {
    //         resultMap.put("result", false);
    //         return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
    //     }


    //     Optional<StoreMenuConnectEntity> storeMenuConnect = smcRepo.findById(data.getSbSmcSeq());
    //     // ShoppingBasketEntity sbEntity = sbRepo.findBySbMiSeqAndStoreMenuConnect(memberInfo.getMiSeq(), storeMenuConnect.get());
        
    //     Long i = 1L;        
    //     ShoppingBasketEntity sbEntity = sbRepo.findBySbMiSeqAndStoreMenuConnectAndSbStatus(memberInfo.getMiSeq(), storeMenuConnect.get(), i);

    //     List<ShoppingBasketOptionEntity> shoppingBasketOptionEntities = sbopRepo.findByShoppingBasket(sbEntity);
    //     sbopRepo.deleteAll(shoppingBasketOptionEntities);

    //     sbRepo.delete(sbEntity);        
        
    //     resultMap.put("result", "로그인 회원의 선택 메뉴 삭제완료");
    //     return new ResponseEntity<>(resultMap, HttpStatus.OK);
    // }

    public ResponseEntity<Object> deleteBasket(DeleteBasketVO data, HttpSession session) {
        MemberEntity memberInfo = (MemberEntity)session.getAttribute("loginUser");  

        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        if(memberInfo == null) {
            resultMap.put("result", false);
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        }


        Optional<StoreMenuConnectEntity> storeMenuConnect = smcRepo.findById(data.getSbSmcSeq());
        // ShoppingBasketEntity sbEntity = sbRepo.findBySbMiSeqAndStoreMenuConnect(memberInfo.getMiSeq(), storeMenuConnect.get());
        
        Long i = 1L;        
        ShoppingBasketEntity sbEntity = sbRepo.findBySbMiSeqAndStoreMenuConnectAndSbStatusAndSbOrderNumber(memberInfo.getMiSeq(), storeMenuConnect.get(), i, data.getSbOrderNumber());

        List<ShoppingBasketOptionEntity> shoppingBasketOptionEntities = sbopRepo.findByShoppingBasket(sbEntity);
        sbopRepo.deleteAll(shoppingBasketOptionEntities);

        sbRepo.delete(sbEntity);        
        
        resultMap.put("result", "로그인 회원의 선택 메뉴 삭제완료");
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }




    public ResponseEntity<Object> changeBasket(ChangeBasketVO data, HttpSession session) {
        MemberEntity memberInfo = (MemberEntity)session.getAttribute("loginUser");  

        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if(memberInfo == null) {
            resultMap.put("result", false);
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        }
        
        Long orderStatus = 1l;

        Optional<StoreMenuConnectEntity> storeMenuConnect = smcRepo.findById(data.getSbSmcSeq());
        ShoppingBasketEntity sbEntity = sbRepo.findBySbMiSeqAndStoreMenuConnectAndSbStatusAndSbOrderNumber(memberInfo.getMiSeq(), storeMenuConnect.get(), orderStatus, data.getSbOrderNumber());
        sbEntity.setSbNumber(data.getSbNumber());
        sbRepo.save(sbEntity);
        
        resultMap.put("result", "로그인 회원의 선택 메뉴 수량변경완료");
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }    
}
