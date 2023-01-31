package com.project1st.starbucks.admin.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project1st.starbucks.admin.entity.MenuEntity;
import com.project1st.starbucks.admin.entity.MenuImageEntity;
import com.project1st.starbucks.admin.repository.MenuImageRepository;
import com.project1st.starbucks.admin.repository.MenuRepository;
import com.project1st.starbucks.menu.entity.MenuBasicInfoEntity;
import com.project1st.starbucks.menu.repository.MenuBasicInfoRepository;
import com.project1st.starbucks.menu.repository.ProductCategoryRepository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Service
public class MenuService {
    @Autowired MenuRepository mbiRepo;
    @Autowired ProductCategoryRepository pcRepo;
    @Autowired MenuRepository mRepo;
    @Autowired MenuImageRepository miRepo;
    @Autowired MenuBasicInfoRepository menuBasicRepo;
    @Value("${file.image.menuimage}") String menu_img_path;
    public void addMenu(
        String mbiName,
        Integer mbiCost,
        String mbiExplain,
        Long mbiPcSeq
    ) {
        MenuEntity menu = MenuEntity.builder()
        .mbiName(mbiName)
        .mbiCost(mbiCost)
        .mbiExplain(mbiExplain)
        .mbiPcSeq(mbiPcSeq).build();
        
        System.out.println("qqq");
        menu = mRepo.save(menu);
    }


}