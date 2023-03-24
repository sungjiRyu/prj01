package com.project1st.starbucks;

import java.security.SecureRandom;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import com.project1st.starbucks.admin.entity.MemberEntity;
// import com.project1st.starbucks.jwt.MakeJwtToken;
import com.project1st.starbucks.admin.repository.MemberInfoRepository;
import com.project1st.starbucks.menu.repository.MenuBasicInfoRepository;
import com.project1st.starbucks.menu.repository.MenuOptionCategoryRepository;
import com.project1st.starbucks.menu.repository.MenuOptionInfoRepository;
import com.project1st.starbucks.store.repository.StoreBasicInfoRepository;
import com.project1st.starbucks.store.repository.StoreMenuConnectRepository;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpSession;
import net.nurigo.java_sdk.Coolsms;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@SpringBootTest
class StarbucksApplicationTests {
    @Autowired MemberInfoRepository memberRepo;
    @Autowired MenuBasicInfoRepository menuRepo;
    @Autowired MenuOptionCategoryRepository OptionCategoryRepo;
    @Autowired MenuOptionInfoRepository OptionsRepo;
    @Autowired StoreBasicInfoRepository storeRepo;
    @Autowired StoreMenuConnectRepository storeMenuRepo;
    

    // <류승지 TEST>
//	@Test
//	void GenerateCertNumber(){
//
//		// Integer certNumLength = 6;
//        Random random = new Random(System.currentTimeMillis());
//        // random.setSeed();
//        // int r = random.nextInt(9000)+1000;
//
//
//		// System.out.println(r);
//		for(int i = 0; i < 30; i++) {
//			System.out.print(random.nextInt(9000)+1000 + " ");
//		}
//	}
//
//    @Test
//    void getRamdomPassword() {
//        char[] charSet = new char[] {
//                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
//                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
//                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
//                '!', '@', '#', '$', '%', '^', '&' };
//
//        StringBuffer sb = new StringBuffer();
//        SecureRandom sr = new SecureRandom();
//        sr.setSeed(System.currentTimeMillis());
//
//        int idx = 0;
//        int len = charSet.length;
//        for (int i=0; i<10; i++) {
//            // idx = (int) (len * Math.random());
//            idx = sr.nextInt(len);    // 강력한 난수를 발생시키기 위해 SecureRandom을 사용한다.
//            sb.append(charSet[idx]);
//        }
//        System.out.println(sb.toString());
//    }
//
//    @Test
//    void gmailSend() {
//
//            // 수신자의 메일주소 설정
//            String recipient = "dhftlcm@naver.com";
//            String code = "abc";
//
//            // 1. 발신자의 메일 계정과 비밀번호 설정
//            final String user = "dhftlcm@gmail.com";
//            final String password = "igmywnnvkqbqyxmd";
//
//            // 2. Property에 SMTP 서버 정보 설정
//            Properties prop = new Properties();
//            prop.put("mail.smtp.host", "smtp.gmail.com");
//            prop.put("mail.smtp.port", 465);
//            prop.put("mail.smtp.auth", "true");
//            prop.put("mail.smtp.ssl.enable", "true");
//            prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
//
//            // 3. SMTP 서버정보와 사용자 정보를 기반으로 Session 클래스의 인스턴스 생성
//            Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
//                protected PasswordAuthentication getPasswordAuthentication() {
//                    return new PasswordAuthentication(user, password);
//                }
//            });
//
//            // 4. Message 클래스의 객체를 사용하여 수신자와 내용, 제목의 메시지를 작성한다.
//            // 5. Transport 클래스를 사용하여 작성한 메세지를 전달한다.
//
//            MimeMessage message = new MimeMessage(session);
//            try {
//                message.setFrom(new InternetAddress(user));
//
//                // 수신자 메일 주소
//                message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
//
//                // Subject
//                message.setSubject("PLAYDDIT verification code");
//
//                // Text
//                message.setText("Welcome to playddit. your code is ["+code+"]");
//
//                Transport.send(message);    // send message
//
//
//            } catch (AddressException e) {
//                e.printStackTrace();
//            } catch (MessagingException e) {
//                e.printStackTrace();
//            }
//        }
//
//    @Test
//    public void GetTempPwd (){
//    // 임시비밀번호 생성
//
//        char[] charSetNum = new char[] {
//                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
//        };
//        char[] charSetAlphabet = new char[] {
//                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
//                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
//
//            };
//        char[] charSetSign = new char[] {
//                '!', '@', '#', '$', '%', '^', '&' };
//
//        StringBuffer sb = new StringBuffer();
//        SecureRandom sr = new SecureRandom();
//        sr.setSeed(new Date().getTime());
//
//        int idx = 0;
//        int lenNum = charSetNum.length;
//        int lenAlp = charSetAlphabet.length;
//        int lenSign= charSetSign.length;
//
//        idx = sr.nextInt(lenNum);    // 강력한 난수를 발생시키기 위해 SecureRandom을 사용한다.
//        sb.append(charSetNum[idx]);
//
//        idx = sr.nextInt(lenSign);    // 강력한 난수를 발생시키기 위해 SecureRandom을 사용한다.
//        sb.append(charSetSign[idx]);
//
//        for (int i=0; i<6; i++) {
//            // idx = (int) (len * Math.random());
//            idx = sr.nextInt(lenAlp);    // 강력한 난수를 발생시키기 위해 SecureRandom을 사용한다.
//            sb.append(charSetAlphabet[idx]);
//        }
//        System.out.println(sb.toString());
//    }
//
//
//
//    // <이도영 TEST>
//    @Test
//	void showMenuList() { // 전체 메뉴 조회
//		System.out.println(menuRepo.findAll());
//	}
//
//	@Test
//	void showMenuDetailList() { // 특정 메뉴 상세 조회
//		System.out.println(menuRepo.findById(1L));
//	}
//
//    @Test
//    void searchMenuByName() { // 메뉴명으로 메뉴 검색
//        String searchKeyword = "노";
//        for(int i=1; i<(menuRepo.findAll().size()); i++){
//            if(menuRepo.findAll().get(i).getMbiName().contains(searchKeyword)){
//                System.out.println(menuRepo.findById((long)i));
//            }
//
//        // //jwttokken생성
//        // @Autowired MakeJwtToken makeJwt;
//        // @Test
//        // public void makeJwtTest(){
//        //  String jwt = makeJwt.makeJwt();
//        //  System.out.println("JWT = "+ jwt);
//
//        // }
//
//        }
//    }
//
//    @Test
//    void showCategoryList() { // 카테고리 목록 조회
//        Long optionCategorySeq = 3L;
//        System.out.println(OptionCategoryRepo.findById(optionCategorySeq).get().getMocName());
//        System.out.println(OptionsRepo.findAllByMoiMocSeq(optionCategorySeq));
//    }
//
////    @Test
////    void showMyStore() { // 내 지점 조회
////        Long memberSeq = 5L;
////        if(memberRepo.findById(memberSeq).get().getMiGroup() != 2) {
////            System.out.println("점주 회원이 아닙니다.");
////        }
////        else {
////            System.out.println(storeRepo.findById(memberRepo.findById(memberSeq).get().getMiSbiSeq()));
////        }
////    }
//
//    @Test
//    void searchStoreByStoreName() { // 지점명으로 지점 찾기
//        String searchStoreName = "동성로점";
//        System.out.println(storeRepo.findBySbiBranchName(searchStoreName));
//    }


}
        


