package com.project1st.starbucks.menu.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.project1st.starbucks.admin.entity.MenuImageEntity;
import com.project1st.starbucks.admin.entity.MenuNutritionEntity;
import com.project1st.starbucks.admin.repository.MenuImageRepository;
import com.project1st.starbucks.admin.repository.MenuNutritionRepository;
import com.project1st.starbucks.menu.entity.MenuBasicInfoEntity;
import com.project1st.starbucks.menu.entity.MenuQrEntity;
import com.project1st.starbucks.menu.repository.MenuBasicInfoRepository;
import com.project1st.starbucks.menu.repository.MenuQrRepository;
import com.project1st.starbucks.menu.repository.ProductCategoryRepository;
import com.project1st.starbucks.menu.vo.MenuStockVO;

import jakarta.servlet.http.HttpServletRequest;

import com.project1st.starbucks.menu.vo.MenuDetailVO;
import com.project1st.starbucks.menu.vo.MenuImageVO;

@Service
public class MenuInfoService {
    @Autowired MenuBasicInfoRepository mbiRepo;
    @Autowired ProductCategoryRepository pcRepo;
    @Autowired MenuQrRepository menuQrRepo;
    @Autowired MenuImageRepository miRepo;
    @Autowired MenuNutritionRepository nutritionRepo;
    @Value("${file.image.menuqr}") String qr_menu_img_path;
    
    // <?????? ?????? ????????????> -> ?????? ???
    public ResponseEntity<Object> menuList(Pageable pageable) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        List<MenuImageEntity> mEntity = miRepo.findAll();
        List<MenuImageVO> menu = new ArrayList<MenuImageVO>();
        for(MenuImageEntity m : mEntity) {
            MenuImageVO menuVO = new MenuImageVO(m);
            menu.add(menuVO);
        }
        resultMap.put("list", menu);

        //????????? ??????
        Page<MenuBasicInfoEntity> page = mbiRepo.findAll(pageable);
        resultMap.put("totalPage", page.getTotalPages());
        resultMap.put("totalCount", page.getTotalElements());
        resultMap.put("currentPage", page.getNumber());
        resultMap.put("status", true);
        resultMap.put("message","?????? ????????? ?????? ???????????????.");
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
    

    // <?????? ?????? ????????????> -> ?????? ???
    public ResponseEntity<Object> munuDetailList(Long menuNo) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        //menu_info ???????????? ???????????? ?????? seq ????????????
        Optional<MenuBasicInfoEntity> menuOptional =  mbiRepo.findById(menuNo);
        if(menuOptional.isEmpty()){
            resultMap.put("status", false);
            resultMap.put("message", menuNo + "??? ????????? ???????????? ????????????.");
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        }
        
        // ?????? ????????? ?????? ?????? ??????
        // MenuBasicInfoEntity menu = mbiRepo.findById(menuNo).get();
        MenuImageEntity menuImage = miRepo.findByMiiNumber(mbiRepo.findById(menuNo).get());
        MenuQrEntity qr = menuQrRepo.findByMqiMbiSeq(menuNo);
        MenuNutritionEntity nutrition = nutritionRepo.findByMnMbiSeq(menuNo);
        MenuDetailVO result = new MenuDetailVO(menuImage, qr, nutrition);
        resultMap.put("detail", result);
        resultMap.put("status", true);
        resultMap.put("message", menuNo + "??? ????????? ??????????????????.");
        return new ResponseEntity<>(resultMap, HttpStatus.CREATED);
    }
        

    // <?????? ????????????> -> ?????? ???
    public ResponseEntity<Object> searchMenuName(String menuName) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        List<MenuBasicInfoEntity> list = mbiRepo.findAll();
        List<MenuDetailVO> result = new ArrayList<MenuDetailVO>();

        for(MenuBasicInfoEntity m : list) {
            if(m.getMbiName().contains(menuName)){
                MenuDetailVO mVo = new MenuDetailVO(miRepo.findByMiiNumber(m), menuQrRepo.findByMqiMbiSeq(m.getMbiSeq()), nutritionRepo.findByMnMbiSeq(m.getMbiSeq()));
                result.add(mVo);
            }
        }

        // ????????? ?????????
        if(result.isEmpty()) {
            resultMap.put("status", false); 
            resultMap.put("message", "???????????? ?????? ??????????????????."); 
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        }
        // ????????? ????????????
        else {
            resultMap.put("totalProductCount", result.size());
            resultMap.put("totalPage", (int)Math.ceil(result.size()/10.0));
            resultMap.put("list", result);
            resultMap.put("status", true); 
            resultMap.put("message", "[" + menuName + "] ?????? ???????????? ???????????? ???????????? ??????????????????."); 
            return new ResponseEntity<>(resultMap, HttpStatus.CREATED);
        }

    }


    // <????????? QR?????? ??????>
    public ResponseEntity<Object> makeQR(String menuName) throws Exception {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        Long menuNo = mbiRepo.findByMbiName(menuName).getMbiSeq();
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
        resultMap.put("message", "??????????????? QR????????? ??????????????????.");
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }


    // <????????? QR?????? ?????? ?????????>
    public static void createQR(String data, String path, String charset, Map hashMap,  int height, int width) throws WriterException, IOException {
        BitMatrix matrix = new MultiFormatWriter().encode(
            new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE, width, height);
        MatrixToImageWriter.writeToFile(matrix,path.substring(path.lastIndexOf('.') + 1),new File(path));
    }


    // <????????? QR????????? ???????????? ?????????>
    public ResponseEntity<Resource> getMenuQRImage (@PathVariable String uri, HttpServletRequest request) throws Exception {
        String filename = null;
        Path folderLocation = null;
        
        filename = getFilenameByUri(uri);
        folderLocation = Paths.get(qr_menu_img_path);

        String[] split = filename.split("\\.");
        String ext = split[split.length - 1];
        String exportName = uri + "." + ext; // ???????????? ??????????????? ?????????. (????????? ????????? ????????? ?????????.)                                                                                                                                                                                                                                                                  

        Path targetFile = folderLocation.resolve(filename); //?????? ????????? ????????? ????????? ????????? ?????? ????????? ????????? ?????????.
        Resource r = null; //????????? ???????????? resource?????? ????????? ????????? ?????????????????????. ( ???????????? ????????? ????????? ???????????? ?????? Resurce ?????? ?????? )
        try {
            r = new UrlResource(targetFile.toUri());
        } catch (Exception e) { e.printStackTrace(); }
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(r.getFile().getAbsolutePath());
            if(contentType == null) {
                contentType = "application/octet-stream";
            }
        } catch (Exception e) { e.printStackTrace(); }
        return ResponseEntity.ok()
        .contentType(MediaType.parseMediaType(contentType))
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=\""+ URLEncoder.encode(exportName, "UTF-8") + "\"")
        .body(r);
    }

    
    // <????????? ????????????>
    public String getFilenameByUri(String uri) {
        // List<MembershipCardQREntity> data = cardQRRepo.findTopByCardqrUriOrderByCardqrSeqDesc(uri);
        return (menuQrRepo.findTopByMqiUriOrderByMqiSeqDesc(uri)).getMqiImageFile();
        // return data.get(0).getCardqrUri();
    }


    // <?????? ????????????>
    
}
