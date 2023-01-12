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

import com.project1st.starbucks.admin.entity.AnnouncementEntity;
import com.project1st.starbucks.admin.repository.AnnouncementRepository;

import io.micrometer.common.lang.Nullable;

@Service
public class AnnouncementService {

    @Autowired AnnouncementRepository aRepo;

    @Value("${file.image.announcement}") String annou_img_path;

        public void addEvent(
            String saTitle,
            @Nullable String saContent,
            MultipartFile saImgFile
        ){
            Calendar c = Calendar.getInstance();
            Path announceFolderLocation = Paths.get(annou_img_path);
            
                String announceOriginFileName = saImgFile.getOriginalFilename();
                String[] iFile = announceOriginFileName.split(("\\."));
                String iExt = iFile[iFile.length-1];
                String iFileName = "";
                for(int i=0;i<iFile.length-1;i++){
                    iFileName += iFile[i];
                }
                String saveAnnounceFileName = "Event"+"_";
                saveAnnounceFileName+=c.getTimeInMillis()+"."+iExt;
                Path announceTargetFile = announceFolderLocation.resolve(saImgFile.getOriginalFilename());
                
                try {
                    Files.copy(saImgFile.getInputStream(), announceTargetFile, StandardCopyOption.REPLACE_EXISTING);
                }   catch(Exception e){e.printStackTrace();}
                
                AnnouncementEntity announ = AnnouncementEntity.builder()
                .saTitle(saTitle)
                .saImgFile(saveAnnounceFileName)
                .saUri(iFileName)
                .saContent(saContent).build();
                announ = aRepo.save(announ);

    }
}
    



