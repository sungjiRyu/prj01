package com.project1st.starbucks.util;

import java.security.SecureRandom;
import java.util.Date;

import org.springframework.stereotype.Service;
@Service
public class GetTempPwd {
    // 임시비밀번호 생성
    public String getRandomPwd() {
        char[] charSetNum = new char[] {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        };
        char[] charSetAlphabet = new char[] {
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',

            };
        char[] charSetSign = new char[] {    
                '!', '@', '#', '$', '%', '^', '&' };

        StringBuffer sb = new StringBuffer();
        SecureRandom sr = new SecureRandom();
        sr.setSeed(new Date().getTime());

        int idx = 0;
        int lenNum = charSetNum.length;
        int lenAlp = charSetAlphabet.length;
        int lenSign= charSetSign.length;
        
        idx = sr.nextInt(lenNum);    // 강력한 난수를 발생시키기 위해 SecureRandom을 사용한다.
        sb.append(charSetNum[idx]);

        idx = sr.nextInt(lenSign);    // 강력한 난수를 발생시키기 위해 SecureRandom을 사용한다.
        sb.append(charSetSign[idx]);

        for (int i=0; i<6; i++) {
            // idx = (int) (len * Math.random());
            idx = sr.nextInt(lenAlp);    // 강력한 난수를 발생시키기 위해 SecureRandom을 사용한다.
            sb.append(charSetAlphabet[idx]);
        }
        return sb.toString();
    }
}
