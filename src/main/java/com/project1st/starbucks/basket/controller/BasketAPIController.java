package com.project1st.starbucks.basket.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project1st.starbucks.admin.entity.MemberEntity;
import com.project1st.starbucks.admin.repository.MenuImageRepository;
import com.project1st.starbucks.basket.entity.ShoppingBasketEntity;
import com.project1st.starbucks.basket.entity.ShoppingBasketOptionEntity;
import com.project1st.starbucks.basket.repository.ShoppingBasketOptionRepository;
import com.project1st.starbucks.basket.repository.ShoppingBasketRepository;
import com.project1st.starbucks.basket.service.BasketService;
import com.project1st.starbucks.basket.vo.ChangeBasketVO;
import com.project1st.starbucks.basket.vo.DeleteBasketVO;
import com.project1st.starbucks.basket.vo.DetailPageBasketVO;
import com.project1st.starbucks.basket.vo.PaymentBasketVO;
import com.project1st.starbucks.menu.repository.MenuBasicInfoRepository;
import com.project1st.starbucks.store.entity.StoreMenuConnectEntity;
import com.project1st.starbucks.store.repository.StoreMenuConnectRepository;

import jakarta.servlet.http.HttpSession;


@RestController
@CrossOrigin("*")

public class BasketAPIController {
    @Autowired ShoppingBasketRepository sbRepo;        
    @Autowired MenuImageRepository imgRepo;
    @Autowired MenuBasicInfoRepository mbiRepo;
    
    @GetMapping("/testa")    
    public Map<String, Object> basket()  {        
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("list", sbRepo.findAll());
        return resultMap;
    }

    @Autowired ShoppingBasketOptionRepository shopopReop;

    @GetMapping("/cart/list") // 장바구니(status = 1), 주문(status = 3) 목록조회
    public Map<String, Object> memberBasket(@RequestParam Long miSeq, @RequestParam Long status) {         
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();       
        // MemberEntity memberInfo = (MemberEntity)session.getAttribute("loginUser");

        // if(memberInfo == null) {
        //     resultMap.put("result", false);
        //     resultMap.put("message", "로그인 해주세요");
        //     return resultMap;
        // }    
        
        // List<ShoppingBasketEntity> list = sbRepo.findBySbMiSeqAndSbStatus(memberInfo.getMiSeq(), status);
        // for(ShoppingBasketEntity e : list) {
        //     Long seq = e.getStoreMenuConnect().getMenu().getMbiSeq();
        //     MenuImageEntity imgEntity = imgRepo.findByMiiNumber(mbiRepo.findById(seq).get());
        //     e.getStoreMenuConnect().getMenu().setFilename(imgEntity.getMiiImgFile());
        //     e.getStoreMenuConnect().getMenu().setUri(imgEntity.getMiiUri());
        // }

        // resultMap.put("memberBasket", list);
        resultMap.put("memberBasket", sbRepo.findBySbMiSeqAndSbStatus(miSeq, status));

        List<ShoppingBasketEntity> basketEntities = new ArrayList<>();
        basketEntities = sbRepo.findBySbMiSeqAndSbStatus(miSeq, status);   
        

        // for(int pricei=0; pricei< basketEntities.size(); pricei++ ){ 
        //     basketPrice = basketEntities.get(pricei).getSbBasketPrice();
        //     basketPrice++;
        //     List<ShoppingBasketOptionEntity> optionEntities = new ArrayList<>();
        //     optionEntities = shopopReop.findByShoppingBasket(basketEntities.get(pricei));
        //     System.out.println(basketPrice);
        //     for(int opti=0; opti< optionEntities.size(); opti++) {
        //         basketOptionPrice = optionEntities.get(opti).getSbOptionPrice();
        //         basketOptionPrice++;
        //         System.out.println(basketOptionPrice);
        //     }            
        // }

        Long basketPrice = 0L;
        Long basketOptionPrice = 0L;

        Long basketPriceSum = 0L;
        Long basketOptionPriceSum = 0L;

        for(ShoppingBasketEntity sbe : basketEntities) {
            basketPrice = sbe.getSbBasketPrice();
            basketPriceSum = basketPriceSum + basketPrice;
            basketPrice++;
            List<ShoppingBasketOptionEntity> optionEntities = new ArrayList<>();
            optionEntities = shopopReop.findByShoppingBasket(sbe);
            for(ShoppingBasketOptionEntity sbeo : optionEntities){
                basketOptionPrice = sbeo.getSbOptionPrice();
                basketOptionPriceSum = basketOptionPriceSum + basketOptionPrice;
                basketOptionPrice++;
            }
        }
        resultMap.put("totalPrice", basketPriceSum + basketOptionPriceSum);
        return resultMap;
    }


    @Autowired StoreMenuConnectRepository smcRepo;
    @PatchMapping("/cart/order") // 주문하는 메소드
    public Map<String, Object> updateOrder(HttpSession session, @RequestParam Long status, @RequestParam Long change, @RequestBody ShoppingBasketEntity shopBasket) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();     
        MemberEntity memberInfo = (MemberEntity)session.getAttribute("loginUser");
        
        if(memberInfo == null) {
            resultMap.put("result", false);
            return resultMap;
        }       
        
        List<ShoppingBasketEntity> changeBasket = sbRepo.findBySbMiSeqAndSbStatus(memberInfo.getMiSeq(), status);
        
        for(ShoppingBasketEntity basket : changeBasket) {
            Date now = new Date();
            basket.setSborderDate(now);
            basket.setSbStatus(change);  
            basket.setSbReceive(shopBasket.getSbReceive());
            basket.setSbRequest(shopBasket.getSbRequest());
            basket.setSbPayment(shopBasket.getSbPayment());            
            sbRepo.save(basket);
            
            StoreMenuConnectEntity storeMenu = smcRepo.findBySmcSeq(basket.getStoreMenuConnect().getSmcSeq());
            storeMenu.setSmcMenuStock((int)(storeMenu.getSmcMenuStock() - basket.getSbNumber()));  
            smcRepo.save(storeMenu);
        } 
        resultMap.put("message", "장바구니에서 주문으로 상태값 변경");
        resultMap.put("stock", "가게 메뉴 재고 변경");
        return resultMap;
    }

    @PatchMapping("/cart/payment")
    public ResponseEntity<Object> paymentBasket(@RequestBody PaymentBasketVO data){
        return basketService.paymentBasket(data);
    }
    

    @Autowired BasketService basketService;
    // @PutMapping("/cart/add") // 디테일 페이지에서 담는 메소드 
    // public ResponseEntity<Object> getDetailBasket(@RequestBody DetailBasketVO data, HttpSession session) {        
    //     return basketService.addDetailBasket(data, session);
    // }

    @PutMapping("/cart/add") // 디테일 페이지에서 담는 메소드 
    public ResponseEntity<Object> getDetailBasket(@RequestBody DetailPageBasketVO data) {        
        return basketService.addDetailBasket(data);
    }

    @DeleteMapping("/cart/delete") // 로그인 회원의 특정 메뉴 삭제
    public ResponseEntity<Object> DeleteBasket(@RequestBody DeleteBasketVO data) {        
        return basketService.deleteBasket(data);
    }

    @PatchMapping("/cart/update") // 로그인 회원의 선택메뉴 수량변경
    public ResponseEntity<Object> ChangeBasket(@RequestBody ChangeBasketVO data) {        
        return basketService.changeBasket(data);
    }
}
