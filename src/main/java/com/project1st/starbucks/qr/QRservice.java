package com.project1st.starbucks.qr;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.project1st.starbucks.menu.entity.MenuQrEntity;
import com.project1st.starbucks.menu.repository.MenuBasicInfoRepository;
import com.project1st.starbucks.menu.repository.MenuQrRepository;


@Service
public class QRservice {
    @Autowired QRrepository qrRepo;
    @Autowired MenuQrRepository menuQrRepo;
    @Autowired MenuBasicInfoRepository menuRepo;
    @Value("${file.image.menuqr}") String qr_menu_img_path;

    // QR코드 생성 -> 메뉴의 qr코드 생성
    public ResponseEntity<Object> makeQR(String menuName) throws Exception {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        Long menuNo = menuRepo.findByMbiName(menuName).getMbiSeq();
        // String data = "http://www.naver.com";
        // String data = "http://localhost:9999/menu/list";
        // String data = "http://192.168.0.104:9999/menu/list";
        String data = "http://haeji.mawani.kro.kr:9999/menu/list/detail?menuNo=" + menuNo;
        String path = "D:\\home\\starbucks\\image\\menuqr\\" + menuName + ".jpg";
        String charset = "UTF-8";
        Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
        hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        createQR(data, path, charset, hashMap, 200, 200);

        MenuQrEntity qrdata = MenuQrEntity.builder()
            .mqiImageFile(menuName + ".jpg")
            .mqiUri(menuName)
            .mqiMbiSeq(menuNo).build();
        qrdata = menuQrRepo.save(qrdata);

        
        resultMap.put("status", true);
        resultMap.put("message", "성공적으로 QR코드를 만들었습니다.");
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    // QR코드 생성 메서드
    public static void createQR(String data, String path, String charset, Map hashMap,  int height, int width) throws WriterException, IOException {
        BitMatrix matrix = new MultiFormatWriter().encode(
            new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE, width, height);
        MatrixToImageWriter.writeToFile(matrix,path.substring(path.lastIndexOf('.') + 1),new File(path));
    }


/*
    // 생성한 QR코드를 DB 테이블에 담기
    // public ResponseEntity<Object> menuQRUpload(MultipartFile file, Long menuSeq) {
    //     Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    //     Path folderLocation = Paths.get(qr_menu_img_path);
    //     String originFileName = file.getOriginalFilename();
    //     String[] split = originFileName.split("\\.");
    //     String ext = split[split.length - 1];
    //     String filename = "";
    //     for(int i=0; i<split.length-1; i++) {
    //         filename += split[i];
    //     }
    //     String saveFilename = "menuqr"+"_";
    //     Calendar c = Calendar.getInstance();
    //     saveFilename += c.getTimeInMillis() + "." + ext;
    //     Path targerFile = folderLocation.resolve(file.getOriginalFilename());
    //     try {
    //         Files.copy(file.getInputStream(), targerFile, StandardCopyOption.REPLACE_EXISTING);
    //     }
    //     catch(Exception e) {
    //         e.printStackTrace();
    //     }
        
    //     MenuQrEntity data = MenuQrEntity.builder()
    //         .mqiImageFile(saveFilename)
    //         .mqiUri(filename)
    //         .mqiMbiSeq(menuSeq).build();
    //     data = menuQrRepo.save(data);
    //     resultMap.put("status", true);
    //     resultMap.put("message", "이미지가 추가되었습니다.");
    //     return new ResponseEntity<>(resultMap, HttpStatus.OK);
    // }
*/

}
