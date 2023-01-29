package com.project1st.starbucks.admin.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project1st.starbucks.admin.entity.MenuImageEntity;
import com.project1st.starbucks.admin.repository.MenuImageRepository;
import com.project1st.starbucks.menu.entity.MenuBasicInfoEntity;
import com.project1st.starbucks.menu.repository.MenuBasicInfoRepository;



@Service

public class MenuImageService {
    @Autowired MenuImageRepository miRepo;
    @Autowired MenuBasicInfoRepository mbiRepo;
    @Value("${file.image.menuimage}") String menu_img_path;
        public void addEvent(
            MultipartFile miiImgFile,
            Long miiNumber
        ){
            Calendar c = Calendar.getInstance();
            Path menuFolderLocation = Paths.get(menu_img_path);
            
                String menuOriginFileName = miiImgFile.getOriginalFilename();
                String[] iFile = menuOriginFileName.split(("\\."));
                String iExt = iFile[iFile.length-1];
                String iFileName = "";
                for(int i=0;i<iFile.length-1;i++){
                    iFileName += iFile[i];
                }
                String saveMenuFileName = "Menu"+"_";
                saveMenuFileName+=c.getTimeInMillis()+"."+iExt;
                Path menuTargetFile = menuFolderLocation.resolve(miiImgFile.getOriginalFilename());
                
                try {
                    Files.copy(miiImgFile.getInputStream(), menuTargetFile, StandardCopyOption.REPLACE_EXISTING);
                }   catch(Exception e){e.printStackTrace();}
                

                
                MenuImageEntity menu = MenuImageEntity.builder()
                .miiNumber(mbiRepo.findById(miiNumber).get())
                .miiImgFile(saveMenuFileName)
                .miiUri(iFileName).build();

                menu = miRepo.save(menu);
    }
}
