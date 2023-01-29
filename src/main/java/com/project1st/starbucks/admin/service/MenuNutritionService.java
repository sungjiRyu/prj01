package com.project1st.starbucks.admin.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project1st.starbucks.admin.entity.MenuNutritionEntity;
import com.project1st.starbucks.admin.repository.MenuNutritionRepository;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class MenuNutritionService {
    @Autowired MenuNutritionRepository mRepo;
    @Value("${file.image.menunutrition}") String nutrition_img_path;
    public void addEvent(
            MultipartFile mnImgFile,
            Long mnMbiSeq
        ){
            Calendar c = Calendar.getInstance();
            Path nutritionFolderLocation = Paths.get(nutrition_img_path);
            
                String nutritionOriginFileName = mnImgFile.getOriginalFilename();
                String[] iFile = nutritionOriginFileName.split(("\\."));
                String iExt = iFile[iFile.length-1];
                String iFileName = "";
                for(int i=0;i<iFile.length-1;i++){
                    iFileName += iFile[i];
                }
                String saveNutritionFileName = "Menu"+"_";
                saveNutritionFileName+=c.getTimeInMillis()+"."+iExt;
                Path nutritionTargetFile = nutritionFolderLocation.resolve(mnImgFile.getOriginalFilename());
                
                try {
                    Files.copy(mnImgFile.getInputStream(), nutritionTargetFile, StandardCopyOption.REPLACE_EXISTING);
                }   catch(Exception e){e.printStackTrace();}
                

                
                MenuNutritionEntity nut = MenuNutritionEntity.builder()
                .mnMbiSeq(mnMbiSeq)
                .mnImgFile(saveNutritionFileName)
                .mnUri(iFileName).build();

                nut = mRepo.save(nut);
    }
}
