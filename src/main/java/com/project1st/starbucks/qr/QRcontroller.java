package com.project1st.starbucks.qr;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/QR")
public class QRcontroller{
    @Autowired QRservice Qservice;
    
    // QR코드 생성 -> 이걸 모든 menu-detail / pay / basket 등으로 // 에러 났을때 코드 추가
    @PostMapping("/new")
    public ResponseEntity<Object> postQR(@RequestParam String menuName) throws Exception{
        return Qservice.makeQR(menuName);
    }


    // 만든 QR코드 이미지를 상세메뉴QR이미지 테이블에 담기
    // @PutMapping("/upload/menu")
    // public ResponseEntity<Object> putMenuQrImage(@RequestPart MultipartFile file, Long menuSeq) {
    //     return Qservice.menuQRUpload(file, menuSeq);
    // }


    // 만든 QR코드 이미지를 멤버십카드 결제로 쓰기
    


        // 만든 QR코드 이미지를 멤버십 충전으로 쓰기

}
