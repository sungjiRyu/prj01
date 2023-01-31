package com.project1st.starbucks.admin.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project1st.starbucks.admin.entity.MemberEntity;
import com.project1st.starbucks.admin.entity.StoreEntity;
import com.project1st.starbucks.admin.repository.MemberRepository;
import com.project1st.starbucks.admin.repository.StoreRepository;
import com.project1st.starbucks.menu.repository.MenuBasicInfoRepository;
import com.project1st.starbucks.store.repository.StoreBasicInfoRepository;
import com.project1st.starbucks.store.repository.StoreMenuConnectRepository;

import io.micrometer.common.lang.Nullable;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Service
public class StoreAdminService {
    @Autowired MemberRepository memberRepo;
    @Autowired StoreRepository sRepo;
    @Autowired StoreBasicInfoRepository sbiRepo; 
    @Autowired MenuBasicInfoRepository mRepo;
    @Autowired StoreMenuConnectRepository smRepo;
        public void addStore (
            String sbiBranchName,
            String sbiAddressBasic,
            String sbiAddressDetail,
            @Nullable String sbiOpenTime,
            @Nullable String sbiCloseTime,
            String sbiCloseDay,
            @Nullable Long sbiMinOrder,
            String sbiCeoName,
            String sbiBusinessAddress,
            String sbiPhone,
            @Nullable String sbiMinDeliveryTime,
            @Nullable String sbiMaxDeliveryTime,
            HttpSession session
        ) {
            // 로그인 회원 정보 객체 생성
            MemberEntity loginUser = (MemberEntity) session.getAttribute("loginUser");
            // 가게정보 객체 생성
            // StoreEntity storeInfo = null;
            
            StoreEntity store = StoreEntity.builder()
                .sbiBranchName(sbiBranchName)
                .sbiAddressBasic(sbiAddressBasic)
                .sbiAddressDetail(sbiAddressDetail)
                .sbiOpenTime(sbiOpenTime)
                .sbiCloseTime(sbiCloseTime)
                .sbiCloseDay(sbiCloseDay)
                .sbiMinOrder(sbiMinOrder)
                .sbiCeoName(sbiCeoName)
                .sbiBusinessAddress(sbiBusinessAddress)
                .sbiPhone(sbiPhone)
                .sbiMinDeliveryTime(sbiMinDeliveryTime)
                .sbiMaxDeliveryTime(sbiMaxDeliveryTime).build();
                store = sRepo.save(store);
                // try{
                //     storeInfo = sRepo.findBySbiBusinessAddress(sbiBusinessAddress);
                // }
                // catch(Exception e){
                //     e.printStackTrace();
                // }
                loginUser.setMiSbiSeq(store.getSbiSeq());
                memberRepo.save(loginUser);
                session.setAttribute("loginUser", loginUser);
        }
    
    @Transactional
    public void deleteStore(Long sbiSeq){
        sRepo.deleteById(sbiSeq);
    }    
}
