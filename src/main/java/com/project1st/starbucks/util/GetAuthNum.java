package com.project1st.starbucks.util;

import java.util.Random;

import org.springframework.stereotype.Service;
@Service
public class GetAuthNum {
     // 인증번호 생성
     public static Integer getAuthNum(){
        Random random = new Random(System.currentTimeMillis());
            Integer r = random.nextInt(9000)+1000;
            return r;
        }
    
}
