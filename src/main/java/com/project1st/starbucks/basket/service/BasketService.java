package com.project1st.starbucks.basket.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project1st.starbucks.admin.entity.MemberEntity;
import com.project1st.starbucks.admin.entity.MenuImageEntity;
import com.project1st.starbucks.admin.repository.MenuImageRepository;
import com.project1st.starbucks.basket.entity.ShoppingBasketEntity;
import com.project1st.starbucks.basket.entity.ShoppingBasketOptionEntity;
import com.project1st.starbucks.basket.repository.ShoppingBasketAddRepository;
import com.project1st.starbucks.basket.repository.ShoppingBasketOptionRepository;
import com.project1st.starbucks.basket.repository.ShoppingBasketRepository;
import com.project1st.starbucks.basket.vo.ChangeBasketVO;
import com.project1st.starbucks.basket.vo.DeleteBasketVO;
import com.project1st.starbucks.basket.vo.DetailPageBasketVO;
import com.project1st.starbucks.basket.vo.PaymentBasketVO;
import com.project1st.starbucks.basket.vo.ShoppingBasketOptionVO;
import com.project1st.starbucks.conpon.entity.CouponInfoEntity;
import com.project1st.starbucks.conpon.repository.CouponInfoRepository;
import com.project1st.starbucks.conpon.repository.CouponMemberInfoRepository;
import com.project1st.starbucks.membershipcard.entity.MembershipCardEntity;
import com.project1st.starbucks.membershipcard.repository.MembershipCardRepository;
import com.project1st.starbucks.menu.entity.MenuOptionInfoEntity;
import com.project1st.starbucks.menu.repository.MenuOptionInfoRepository;
import com.project1st.starbucks.store.entity.StoreMenuConnectEntity;
import com.project1st.starbucks.store.repository.StoreMenuConnectRepository;

import jakarta.transaction.Transactional;

@Service
public class BasketService {
    @Autowired ShoppingBasketRepository sbRepo;
    @Autowired ShoppingBasketOptionRepository sbopRepo;
    @Autowired StoreMenuConnectRepository smcRepo;
    @Autowired ShoppingBasketAddRepository sbAddRepo;
    @Autowired StoreMenuConnectRepository storeMenuConnectRepo;
    @Autowired MenuImageRepository menuImageRepository;

    // public ResponseEntity<Object> addDetailBasket(DetailBasketVO data, HttpSession session) {       
    //     MemberEntity memberInfo = (MemberEntity)session.getAttribute("loginUser");
    //     Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

    //     if(memberInfo == null) {
    //         resultMap.put("result", false);
    //         return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
    //     }

    //     long e = 1;
    //     Optional<StoreMenuConnectEntity> storeMenuConnect = smcRepo.findById(data.getSbSmcSeq());
    //     ShoppingBasketEntity sbEntity = sbRepo.findBySbMiSeqAndStoreMenuConnectAndSbStatus(miSeq, storeMenuConnect.get(), e);        

    //     if(sbEntity != null) {
    //         sbEntity.setSbNumber(sbEntity.getSbNumber() + 1);
    //         sbRepo.save(sbEntity);
    //         resultMap.put("result", true);
    //         resultMap.put("message", "수량추가");
    //     }

    //     else { long i = 1;        
    //     ShoppingBasketEntity entity = ShoppingBasketEntity.builder().sbMiSeq(miSeq).sbStatus(i).sbNumber(data.getSbNumber())
    //                                             .storeMenuConnect(smcRepo.findById(data.getSbSmcSeq()).get()).build();
    //     sbRepo.save(entity);
    //     resultMap.put("result", true);
    //     resultMap.put("message", "장바구니 추가 완료");
    //     resultMap.put("entity", entity);        
    //     }

    //     return new ResponseEntity<>(resultMap, HttpStatus.OK);
    // }
    @Autowired MenuOptionInfoRepository menuOptionInfoRepo;
    

    public ResponseEntity<Object> addDetailBasket(DetailPageBasketVO data) {   

        // MemberEntity memberInfo = (MemberEntity)session.getAttribute("loginUser");

        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        // if(memberInfo == null) {
        //     resultMap.put("result", false);
        //     resultMap.put("message", "로그인 해주세요");
        //     return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        // }

        // else {             
            // long e = 1;
            // Optional<StoreMenuConnectEntity> storeMenuConnect = smcRepo.findById(data.getShoppingBasketVo().getSbSmcSeq());
            // // ShoppingBasketEntity sbEntity = sbRepo.findBySbMiSeqAndStoreMenuConnectAndSbStatus(miSeq, storeMenuConnect.get(), e);
            
            // // List<ShoppingBasketOptionEntity> sbOptionEntity = sbopRepo.findByShoppingBasket(sbEntity);


            // if(sbEntity != null && sbAddRepo.findBySboOptionOrderNumber(sbEntity.getSbOrderNumber()) != null) {
            //     sbEntity.setSbNumber(sbEntity.getSbNumber() + 1);
            //     resultMap.put("result", true);
            //     resultMap.put("message", "수량추가");
            //     return new ResponseEntity<>(resultMap, HttpStatus.OK);
            // }
            // else {
            Integer orderNo = (int)(Math.random()*10000000);

            StoreMenuConnectEntity storeMenuConnectEntity = smcRepo.findBySmcSeq(data.getShoppingBasketVo().getSbSmcSeq());

            MenuImageEntity menuImageEntity = menuImageRepository.findByMiiNumber(storeMenuConnectEntity.getMenu());

            

                if(data.getShoppingBasketOption() == null) {
                    long i = 1;        
                    ShoppingBasketEntity entity = ShoppingBasketEntity.builder().sbMiSeq(data.getMiSeq()).sbStatus(i).sbNumber(data.getShoppingBasketVo().getSbNumber())
                                                .sbOrderNumber(orderNo)
                                                .storeMenuConnect(smcRepo.findById(data.getShoppingBasketVo().getSbSmcSeq()).get())
                                                .menuImageName(menuImageEntity.getMiiImgFile())
                                                .menuImageUri(menuImageEntity.getMiiUri())
                                                .sbBasketPrice(smcRepo.findById(data.getShoppingBasketVo().getSbSmcSeq()).get().getMenu().getMbiCost() * data.getShoppingBasketVo().getSbNumber())
                                                .optionIncludePrice(smcRepo.findById(data.getShoppingBasketVo().getSbSmcSeq()).get().getMenu().getMbiCost() * data.getShoppingBasketVo().getSbNumber())
                                                .build();
                    sbRepo.save(entity);
                    resultMap.put("result", true);
                    resultMap.put("message", "장바구니 추가 완료");
                    // resultMap.put("entity", entity);  
                    return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
                }        

                MenuOptionInfoEntity menuOptionInfoEntity = menuOptionInfoRepo.findByMoiSeq(data.getShoppingBasketOption().get(0).getSboMoiSeq());
                int optionCost = menuOptionInfoEntity.getMoiCost();
                Long optionCostCost = Long.valueOf(optionCost);

                long i = 1;        
                ShoppingBasketEntity entity = ShoppingBasketEntity.builder().sbMiSeq(data.getMiSeq()).sbStatus(i).sbNumber(data.getShoppingBasketVo().getSbNumber())
                                                .sbOrderNumber(orderNo)
                                                .menuImageName(menuImageEntity.getMiiImgFile())
                                                .menuImageUri(menuImageEntity.getMiiUri())
                                                .sbBasketPrice(smcRepo.findById(data.getShoppingBasketVo().getSbSmcSeq()).get().getMenu().getMbiCost() * data.getShoppingBasketVo().getSbNumber())
                                                .optionIncludePrice(smcRepo.findById(data.getShoppingBasketVo().getSbSmcSeq()).get().getMenu().getMbiCost() * data.getShoppingBasketVo().getSbNumber()                                             
                                                + (optionCostCost * data.getShoppingBasketOption().get(0).getSboNumber()))                                                
                                                .storeMenuConnect(smcRepo.findById(data.getShoppingBasketVo().getSbSmcSeq()).get()).build();
                sbRepo.save(entity);
                resultMap.put("result", true);
                resultMap.put("message", "장바구니 추가 완료");
                // resultMap.put("entity", entity);  

                for(ShoppingBasketOptionVO opt : data.getShoppingBasketOption()) {
                    MenuOptionInfoEntity menuOptionEntity = menuOptionInfoRepo.findByMoiSeq(opt.getSboMoiSeq()); 

                    ShoppingBasketOptionEntity optionEntity =ShoppingBasketOptionEntity.builder().sboNumber(opt.getSboNumber())
                                                            .sboOptionOrderNumber(entity.getSbOrderNumber())
                                                            .menuOption(menuOptionEntity).shoppingBasket(entity)
                                                            .sbOptionPrice(menuOptionEntity.getMoiCost() * opt.getSboNumber())
                                                            .build();        
                    sbopRepo.save(optionEntity);  
                    resultMap.put("optionMessage", "옵션추가 완료");
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
    //     // ShoppingBasketEntity sbEntity = sbRepo.findBySbMiSeqAndStoreMenuConnect(miSeq, storeMenuConnect.get());
        
    //     Long i = 1L;        
    //     ShoppingBasketEntity sbEntity = sbRepo.findBySbMiSeqAndStoreMenuConnectAndSbStatus(miSeq, storeMenuConnect.get(), i);

    //     List<ShoppingBasketOptionEntity> shoppingBasketOptionEntities = sbopRepo.findByShoppingBasket(sbEntity);
    //     sbopRepo.deleteAll(shoppingBasketOptionEntities);

    //     sbRepo.delete(sbEntity);        
        
    //     resultMap.put("result", "로그인 회원의 선택 메뉴 삭제완료");
    //     return new ResponseEntity<>(resultMap, HttpStatus.OK);
    // }
    @Transactional
    public ResponseEntity<Object> deleteBasket(DeleteBasketVO data) {
        // MemberEntity memberInfo = (MemberEntity)session.getAttribute("loginUser");  

        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        // if(memberInfo == null) {
        //     resultMap.put("result", false);
        //     return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        // }


        Optional<StoreMenuConnectEntity> storeMenuConnect = smcRepo.findById(data.getSbSmcSeq());
        // ShoppingBasketEntity sbEntity = sbRepo.findBySbMiSeqAndStoreMenuConnect(miSeq, storeMenuConnect.get());
        
        Long i = 1L;        
        ShoppingBasketEntity sbEntity = sbRepo.findBySbMiSeqAndStoreMenuConnectAndSbStatusAndSbOrderNumber(data.getMiSeq(), storeMenuConnect.get(), i, data.getSbOrderNumber());

        List<ShoppingBasketOptionEntity> shoppingBasketOptionEntities = sbopRepo.findByShoppingBasket(sbEntity);
        sbopRepo.deleteAll(shoppingBasketOptionEntities);

        sbRepo.delete(sbEntity);        
        
        resultMap.put("result", "로그인 회원의 선택 메뉴 삭제완료");
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }




    public ResponseEntity<Object> changeBasket(ChangeBasketVO data) {
        // MemberEntity memberInfo = (MemberEntity)session.getAttribute("loginUser");
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        // if(memberInfo == null) {
        //     resultMap.put("result", false);
        //     return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        // }
        
        Long orderStatus = 1l;

        Optional<StoreMenuConnectEntity> storeMenuConnect = smcRepo.findById(data.getSbSmcSeq());
        ShoppingBasketEntity sbEntity = sbRepo.findBySbMiSeqAndStoreMenuConnectAndSbStatusAndSbOrderNumber(data.getMiSeq(), storeMenuConnect.get(), orderStatus, data.getSbOrderNumber());
        sbEntity.setSbNumber(data.getSbNumber());
        sbEntity.setSbBasketPrice(data.getSbNumber() * sbEntity.getStoreMenuConnect().getMenu().getMbiCost());
        sbRepo.save(sbEntity);
        
        resultMap.put("result", "로그인 회원의 선택 메뉴 수량변경완료");
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }    

    @Autowired MembershipCardRepository memCardRepo;
    @Autowired CouponMemberInfoRepository couponMemberRepo;
    @Autowired CouponInfoRepository couponInfoRepo;


    public ResponseEntity<Object> paymentBasket(PaymentBasketVO data) {
        
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        // MemberEntity memberInfo = (MemberEntity)session.getAttribute("loginUser");  
        // if(memberInfo == null) {
        //     resultMap.put("result", false);
        //     return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        // }

        Long basketStatus = 1L;

        List<ShoppingBasketEntity> basketEntities = new ArrayList<>();
        basketEntities = sbRepo.findBySbMiSeqAndSbStatus(data.getMiSeq(), basketStatus); 

        if(basketEntities.size() == 0) {
            resultMap.put("result", false);
            resultMap.put("message", "장바구니에 상품이 없습니다");
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        }
        
        Long basketPrice = 0L;
        Long basketOptionPrice = 0L;

        Long basketPriceSum = 0L;
        Long basketOptionPriceSum = 0L;

        for(ShoppingBasketEntity sbe : basketEntities) {
            basketPrice = sbe.getSbBasketPrice();
            basketPriceSum = basketPriceSum + basketPrice;
            basketPrice++;
            List<ShoppingBasketOptionEntity> optionEntities = new ArrayList<>();
            optionEntities = sbopRepo.findByShoppingBasket(sbe);
            for(ShoppingBasketOptionEntity sbeo : optionEntities){
                basketOptionPrice = sbeo.getSbOptionPrice();
                basketOptionPriceSum = basketOptionPriceSum + basketOptionPrice;
                basketOptionPrice++;
            }
        }
        
        


        for(ShoppingBasketEntity basket : basketEntities) {
            Date now = new Date();
            basket.setSborderDate(now);
            basket.setSbStatus(3L);
            basket.setSbRequest(data.getSbRequest());                        
            basket.setSbReceive(data.getSbReceive());
            basket.setSbPayment(data.getSbPayment());
            sbRepo.save(basket);            
            
            StoreMenuConnectEntity storeMenu = smcRepo.findBySmcSeq(basket.getStoreMenuConnect().getSmcSeq());
            storeMenu.setSmcMenuStock((int)(storeMenu.getSmcMenuStock() - basket.getSbNumber()));  
            smcRepo.save(storeMenu);
        }
        
        if(data.getCouponUse() == 0) {
            MembershipCardEntity memcardEntity;
            memcardEntity =  memCardRepo.findByCardMiSeq(data.getMiSeq());
            int totalPrice = (int)(basketPriceSum + basketOptionPriceSum);
            
            if(memcardEntity.getCardMoney() < totalPrice) {
                resultMap.put("result", false);
                resultMap.put("result", "멤버쉽 카드 잔액을 확인해주세요");
                return new ResponseEntity<>(resultMap, HttpStatus.OK);
            }

            memcardEntity.setCardMoney(memcardEntity.getCardMoney() - totalPrice);        

            resultMap.put("paymentCost", "결제금액" + totalPrice);
            resultMap.put("cardBalance", "결제후 카드잔액" + (memcardEntity.getCardMoney() - totalPrice));
            resultMap.put("menuStock", "가게 재고 감소");
            resultMap.put("result", true);            
        } 

        else if(data.getCouponUse() == 1) {
            MembershipCardEntity memcardEntity;
            CouponInfoEntity couponInfoEntity;

            couponInfoEntity = couponInfoRepo.findByCiSeq(data.getCouponNumber());

            memcardEntity =  memCardRepo.findByCardMiSeq(data.getMiSeq());
            int totalPrice = (int)(basketPriceSum + basketOptionPriceSum - couponInfoEntity.getCiDiscount());            
            memcardEntity.setCardMoney(memcardEntity.getCardMoney() - totalPrice);              

            resultMap.put("paymentCost", "결제금액" + totalPrice);
            resultMap.put("couponUse", "쿠폰적용 금액" + couponInfoEntity.getCiDiscount());
            resultMap.put("cardBalance", "결제후 카드잔액" + (memcardEntity.getCardMoney() - totalPrice));
            resultMap.put("menuStock", "가게 재고 감소");
            resultMap.put("message", "결제완료");
            resultMap.put("result", true);        
        }       

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
}
