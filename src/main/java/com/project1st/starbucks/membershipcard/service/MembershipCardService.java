package com.project1st.starbucks.membershipcard.service;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
import com.project1st.starbucks.admin.entity.MemberEntity;
import com.project1st.starbucks.admin.repository.MemberRepository;
import com.project1st.starbucks.membershipcard.entity.MembershipCardEntity;
import com.project1st.starbucks.membershipcard.entity.MembershipCardImageEntity;
import com.project1st.starbucks.membershipcard.entity.MembershipCardQREntity;
import com.project1st.starbucks.membershipcard.repository.MembershipCardImageRepository;
import com.project1st.starbucks.membershipcard.repository.MembershipCardRepository;
import com.project1st.starbucks.membershipcard.repository.MembershipcardQRRepository;
import com.project1st.starbucks.membershipcard.vo.MembershipCardDetailVO;
import com.project1st.starbucks.membershipcard.vo.MembershipCardVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class MembershipCardService {
    @Autowired MemberRepository memberRepo;
    @Autowired MembershipCardRepository cardRepo;
    @Autowired MembershipCardImageRepository cardImageRepo;
    @Autowired MembershipcardQRRepository cardQRRepo;
    @Value("${file.image.cardqr}") String qr_card_img_path;

    //카드 + 카드 QR이미지 생성 -> 완료 ♥
    public ResponseEntity<Object> createNewMembershipCard(MembershipCardEntity data, Long memberNo) throws Exception {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        // 세션으로 로그인 정보 불러오기
        MemberEntity memberInfo = memberRepo.findByMiSeq(memberNo);
        if (memberInfo == null) {
            resultMap.put("status", false);
            resultMap.put("message", "멤버십 카드 생성을 위해 로그인 해주세요.");
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        }
        //이미 회원에게 멤버십카드가 존재한다면 생성에러
        if (cardRepo.findByCardMiSeq(memberInfo.getMiSeq()) != null) {
            resultMap.put("status", false);
            resultMap.put("message", "이미 멤버십 카드가 존재합니다.");
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        }

        // 로그인했다면 멤버십카드 생성
        data.setCardMiSeq(memberInfo.getMiSeq());
        cardRepo.save(data);
        
        // 멤버십 카드 생성과 동시에 멤버십카드 충전 QR코드가 생성
        Path cardqrLocation = Paths.get(qr_card_img_path);
        // String source = "http://192.168.0.55:3000/qrmob?miSeq=" + memberNo;
        String source = "http://haeji.mawani.kro.kr:3389/qrmob?miSeq=" + memberNo;
        // String path = "/home/starbucks/image/cardqr/MembershipCard_MemberNo_" + data.getCardMiSeq() + ".jpg";
        String path = cardqrLocation + "/MembershipCard_MemberNo_" + data.getCardMiSeq() + ".jpg";
        String charset = "UTF-8";
        Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
        hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        createQR(source, path, charset, hashMap, 200, 200);

        MembershipCardQREntity qrdata = MembershipCardQREntity.builder()
            .cardqrFile("MembershipCard_MemberNo_" + data.getCardMiSeq() + ".jpg")
            .cardqrUri("MembershipCard_MemberNo_" + data.getCardMiSeq())
            .cardqrMiSeq(memberInfo.getMiSeq()).build();
        qrdata = cardQRRepo.save(qrdata);

        resultMap.put("status", true);
        resultMap.put("message", "카드 등록이 완료되었습니다.");
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }


    //QR코드 생성 메서드 -> 완료 ♥
    public static void createQR(String source, String path, String charset, Map hashMap,  int height, int width) throws WriterException, IOException {
        BitMatrix matrix = new MultiFormatWriter().encode(
        new String(source.getBytes(charset), charset),
        BarcodeFormat.QR_CODE, width, height);

        MatrixToImageWriter.writeToFile(matrix,path.substring(path.lastIndexOf('.') + 1),new File(path));
    }


     // 카드 이미지 다운로드 하기 -> 완료 ♥
    public ResponseEntity<Resource> getCardQRImage (@PathVariable String uri, HttpServletRequest request) throws Exception {
        String filename = null;
        Path folderLocation = null;

        filename = getFilenameByUri(uri);
        folderLocation = Paths.get(qr_card_img_path);

        String[] split = filename.split("\\.");
        String ext = split[split.length - 1];
        String exportName = uri + "." + ext; // 내보내줄 파일이름은 다르다. (내보낼 파일의 이름을 만든다.)                                                                                                                                                                                                                                                                  

        Path targetFile = folderLocation.resolve(filename); //폴더 경로와 파일의 이름을 합쳐서 목표 파일의 경로를 만든다.
        Resource r = null; //파일을 가져와서 resource라는 형태로 바꿔서 내보내줘야한다. ( 다운로드 가능한 형태로 변환하기 위한 Resurce 객체 생성 )
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

    
    // 파일명 가져오기 -> 완료 ♥
    public String getFilenameByUri(String uri) {
        // List<MembershipCardQREntity> data = cardQRRepo.findTopByCardqrUriOrderByCardqrSeqDesc(uri);
        return (cardQRRepo.findTopByCardqrUriOrderByCardqrSeqDesc(uri)).getCardqrFile();
        // return data.get(0).getCardqrUri();
    }



    //카드충전 (QR코드 인식 후) -> 완료 ♥
    public ResponseEntity<Object> chargeMembershipCard(Integer money, Long memberNo){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        // 세션으로 로그인 정보 불러오기
        MemberEntity memberInfo = memberRepo.findByMiSeq(memberNo);
        if (memberInfo == null) {
            resultMap.put("status", false);
            resultMap.put("message", "멤버십 카드 충전을 위해 로그인 해주세요.");
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        }
        // 멤버십 카드가 존재하지 않는다면 에러
        if(cardRepo.findByCardMiSeq(memberInfo.getMiSeq()) == null) {
            resultMap.put("status", false);
            resultMap.put("message", "멤버십 카드가 존재하지 않습니다. 멤버십 카드를 발급해주세요.");
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        }
        // 멤버십 카드 충전
        MembershipCardEntity cardEntity = cardRepo.findByCardMiSeq(memberInfo.getMiSeq());
        cardEntity.setCardMoney(cardEntity.getCardMoney() + money);
        cardRepo.save(cardEntity);
        resultMap.put("status", true);
        resultMap.put("message", money + "원이 충전되었습니다. [카드 잔액 : " + cardEntity.getCardMoney() +"원]");
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
    
    
    //카드조회  -> 완료 ♥
    public ResponseEntity<Object> detailMembershipCard(Long memberNo){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        // 세션으로 로그인 정보 불러오기
        MemberEntity memberInfo = memberRepo.findByMiSeq(memberNo);
        if (memberInfo == null) {
            resultMap.put("status", false);
            resultMap.put("message", "멤버십 카드 조회를 위해 로그인 해주세요.");
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        }
        // 멤버십 카드가 존재하지 않는다면 에러
        if(cardRepo.findByCardMiSeq(memberInfo.getMiSeq()) == null) {
            resultMap.put("status", false);
            resultMap.put("message", "멤버십 카드가 존재하지 않습니다. 멤버십 카드를 발급해주세요.");
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        }
        // 로그인했다면 멤버십카드 조회하기
        // MembershipCardVO cardVO = new MembershipCardVO(cardRepo.findByCardMiSeq(memberInfo.getMiSeq()));
        MembershipCardEntity card = cardRepo.findByCardMiSeq(memberInfo.getMiSeq());
        MembershipCardImageEntity cardImage = cardImageRepo.findById(card.getCardImage()).get();
        MembershipCardQREntity cardQr = cardQRRepo.findByCardqrMiSeq(memberInfo.getMiSeq());
        MembershipCardDetailVO result = new MembershipCardDetailVO(card, cardImage, cardQr);
        resultMap.put("detail", result);
        resultMap.put("status", true);
        resultMap.put("message", memberInfo.getMiName() + " [닉네임:"+ memberInfo.getMiNickname() +"] 님의 멤버십 카드 상세조회 입니다.");
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }


    //카드삭제 -> 완료 ♥
    public ResponseEntity<Object> deleteMembershipCard(Long memberNo){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        // 세션으로 로그인 정보 불러오기
        MemberEntity memberInfo = memberRepo.findByMiSeq(memberNo);
        if (memberInfo == null) {
            resultMap.put("status", false);
            resultMap.put("message", "멤버십 카드 삭제를 위해 로그인 해주세요.");
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        }
        // 멤버십 카드가 존재하지 않는다면 에러
        if(cardRepo.findByCardMiSeq(memberInfo.getMiSeq()) == null) {
            resultMap.put("status", false);
            resultMap.put("message", "멤버십 카드가 존재하지 않습니다. 멤버십 카드를 발급해주세요.");
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        }
        // 카드에 잔액이 존재한다면 에러뜨기
        MembershipCardEntity cardEntity = cardRepo.findByCardMiSeq(memberInfo.getMiSeq());
        // if(cardEntity.getCardMoney() != 0) {
        //     resultMap.put("status", false);
        //     resultMap.put("message", "멤버십 카드에 잔액이 존재합니다.");
        //     return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        // }
        MembershipCardQREntity cardQrEntity = cardQRRepo.findByCardqrMiSeq(memberNo);
        // 로그인했다면 멤버십카드 삭제하기
        cardRepo.delete(cardEntity);
        cardQRRepo.delete(cardQrEntity);
        resultMap.put("status", true);
        resultMap.put("message", "멤버십 카드가 삭제되었습니다.");
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }


    
}