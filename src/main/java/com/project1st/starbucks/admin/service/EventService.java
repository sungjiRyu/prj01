package com.project1st.starbucks.admin.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project1st.starbucks.admin.entity.EventDetailEntity;
import com.project1st.starbucks.admin.entity.EventEntity;
import com.project1st.starbucks.admin.repository.EventDetailRepository;
import com.project1st.starbucks.admin.repository.EventRepository;
import com.project1st.starbucks.admin.repository.MemberRepository;



@Service
public class EventService {
    @Autowired EventRepository eventRepo;
    @Autowired EventDetailRepository detailRepo;
    @Autowired MemberRepository memberRepo;

    @Value("${file.image.event}") String event_img_path;
    @Value("${file.image.eventdetail}") String detail_img_path;

        public void addEvent(
            LocalDate evStartDate,
            LocalDate evEndDate,
            LocalDate ediStartDate,
            LocalDate ediEndDate,
            @Nullable String evContent,
            @Nullable String ediContent,
            MultipartFile evFile,
            MultipartFile edFile
        ){
            Calendar c = Calendar.getInstance();
            Path eventFolderLocation = Paths.get(event_img_path);
            Path detailFolderLocation = Paths.get(detail_img_path);
            
                String eventOriginFileName = evFile.getOriginalFilename();
                String[] iFile = eventOriginFileName.split(("\\."));
                String iExt = iFile[iFile.length-1];
                String iFileName = "";
                for(int i=0;i<iFile.length-1;i++){
                    iFileName += iFile[i];
                }
                String saveEventFileName = "Event"+"_";
                saveEventFileName+=c.getTimeInMillis()+"."+iExt;
                Path eventTargetFile = eventFolderLocation.resolve(evFile.getOriginalFilename());
                
                try {
                    Files.copy(evFile.getInputStream(), eventTargetFile, StandardCopyOption.REPLACE_EXISTING);
                }   catch(Exception e){e.printStackTrace();}
                
                EventEntity event = EventEntity.builder()
                .evStartDate(evStartDate)
                .evEndDate(evEndDate)
                .evContent(evContent)
                .evUri(iFileName)
                .evFile(saveEventFileName).build();

                // event.setEvSeq(event.getEvSeq());
                // event.setEvContent(event.getEvContent());
                event = eventRepo.save(event);

                String detailOriginFileName = edFile.getOriginalFilename();
                String[] dFile = detailOriginFileName.split("\\.");
                String dExt = dFile[dFile.length-1];
                String dFileName = "";
                for(int i=0;i<dFile.length-1;i++) {
                    dFileName += dFile[i];
                }
                String saveDetailFileName = "Detail"+"_";
                saveDetailFileName+=c.getTimeInMillis()+"."+dExt;
                Path detailTargetFile = detailFolderLocation.resolve(edFile.getOriginalFilename());

                try {
                    Files.copy(edFile.getInputStream(), detailTargetFile, StandardCopyOption.REPLACE_EXISTING);
                } catch(Exception e){e.printStackTrace();}

                EventDetailEntity detail = EventDetailEntity.builder()
                .ediStartDate(ediStartDate)
                .ediEndDate(ediEndDate)
                .ediContents(ediContent)
                .ediUri(dFileName)
                .edFile(saveDetailFileName).build();
                detail = detailRepo.save(detail);
    }
}
    



