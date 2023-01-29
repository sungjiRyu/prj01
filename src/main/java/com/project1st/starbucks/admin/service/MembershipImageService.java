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

import com.project1st.starbucks.admin.entity.MembershipImageEntity;
import com.project1st.starbucks.admin.repository.MembershipImageRepository;

@Service
public class MembershipImageService {
    @Autowired MembershipImageRepository mRepo;
    @Value("${file.image.membership}") String membership_img_path;
        public void addEvent(
            MultipartFile ciImgFile,
            String ciName
        ){
            Calendar c = Calendar.getInstance();
            Path membershipFolderLocation = Paths.get(membership_img_path);
            
                String membershipOriginFileName = ciImgFile.getOriginalFilename();
                String[] iFile = membershipOriginFileName.split(("\\."));
                String iExt = iFile[iFile.length-1];
                String iFileName = "";
                for(int i=0;i<iFile.length-1;i++){
                    iFileName += iFile[i];
                }
                String saveMembershipFileName = "Membership"+"_";
                saveMembershipFileName+=c.getTimeInMillis()+"."+iExt;
                Path membershipTargetFile = membershipFolderLocation.resolve(ciImgFile.getOriginalFilename());
                
                try {
                    Files.copy(ciImgFile.getInputStream(), membershipTargetFile, StandardCopyOption.REPLACE_EXISTING);
                }   catch(Exception e){e.printStackTrace();}
                

                
                MembershipImageEntity membership = MembershipImageEntity.builder()
                .ciName(ciName)
                .ciImgFile(saveMembershipFileName)
                .ciUri(iFileName).build();

                membership = mRepo.save(membership);
    }
}

