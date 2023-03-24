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

import com.project1st.starbucks.admin.entity.StoreImageEntity;
import com.project1st.starbucks.admin.repository.StoreImageRepository;

@Service
public class StoreImageService {
    @Autowired StoreImageRepository sRepo;
    @Value("${file.image.store}") String store_img_path;
        public void addEvent(
            MultipartFile siImgFile,
            Long siNumber
        ){
            Calendar c = Calendar.getInstance();
            Path menuFolderLocation = Paths.get(store_img_path);
            
                String menuOriginFileName = siImgFile.getOriginalFilename();
                String[] iFile = menuOriginFileName.split(("\\."));
                String iExt = iFile[iFile.length-1];
                String iFileName = "";
                for(int i=0;i<iFile.length-1;i++){
                    iFileName += iFile[i];
                }
                String saveStoreFileName = "Store"+"_";
                saveStoreFileName+=c.getTimeInMillis()+"."+iExt;
                Path menuTargetFile = menuFolderLocation.resolve(siImgFile.getOriginalFilename());
                
                try {
                    Files.copy(siImgFile.getInputStream(), menuTargetFile, StandardCopyOption.REPLACE_EXISTING);
                }   catch(Exception e){e.printStackTrace();}
                
                StoreImageEntity menu = StoreImageEntity.builder()
                .siNumber(siNumber)
                .siImgFile(saveStoreFileName)
                .siUri(iFileName).build();

                menu = sRepo.save(menu);
    }
}