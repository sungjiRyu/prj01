package com.project1st.starbucks.basket.controller;

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
import com.project1st.starbucks.basket.entity.ShoppingBasketEntity;
import com.project1st.starbucks.basket.repository.ShoppingBasketRepository;
import com.project1st.starbucks.basket.service.BasketService;
import com.project1st.starbucks.basket.vo.ChangeBasketVO;
import com.project1st.starbucks.basket.vo.DeleteBasketVO;
import com.project1st.starbucks.basket.vo.DetailBasketVO;
import com.project1st.starbucks.basket.vo.DetailPageBasketVO;
import com.project1st.starbucks.store.entity.StoreMenuConnectEntity;
import com.project1st.starbucks.store.repository.StoreMenuConnectRepository;

import jakarta.servlet.http.HttpSession;


@RestController
@CrossOrigin("*")

public class BasketAPIController {
    @Autowired ShoppingBasketRepository sbRepo;        
    @GetMapping("/testa")    
     public Map<String, Object> basket()  {        
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("list", sbRepo.findAll());
        return resultMap;
     }

     @GetMapping("/cart/list") // 장바구니(status = 1), 주문(status = 5) 목록조회
     public Map<String, Object> memberBasket(HttpSession session, @RequestParam Long status) {        
         Map<String, Object> resultMap = new LinkedHashMap<String, Object>();         
         MemberEntity memberInfo = (MemberEntity)session.getAttribute("loginUser");
         resultMap.put("memberBasket", sbRepo.findBySbMiSeqAndSbStatus(memberInfo.getMiSeq(), status));
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
    

    @Autowired BasketService basketService;
    // @PutMapping("/cart/add") // 디테일 페이지에서 담는 메소드 
    // public ResponseEntity<Object> getDetailBasket(@RequestBody DetailBasketVO data, HttpSession session) {        
    //     return basketService.addDetailBasket(data, session);
    // }

    @PutMapping("/cart/add") // 디테일 페이지에서 담는 메소드 
    public ResponseEntity<Object> getDetailBasket(@RequestBody DetailPageBasketVO data, HttpSession session) {        
        return basketService.addDetailBasket(data, session);
    }

    @DeleteMapping("/cart/delete") // 로그인 회원의 특정 메뉴 삭제
    public ResponseEntity<Object> DeleteBasket(@RequestBody DeleteBasketVO data, HttpSession session) {        
        return basketService.deleteBasket(data, session);
    }

    @PatchMapping("/cart/update") // 로그인 회원의 선택메뉴 수량변경
    public ResponseEntity<Object> ChangeBasket(@RequestBody ChangeBasketVO data, HttpSession session) {        
        return basketService.changeBasket(data, session);
    }

}
