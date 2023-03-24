package com.project1st.starbucks.util;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import net.nurigo.java_sdk.exceptions.CoolsmsException;
@Service
public class SendMessage {
    public void sendAuthNumbyPhone(String phoneNum, Integer certificationNum){
        String api_key = "NCSNSK6ORRAURS62";
            String api_secret = "PIHNMNQAHHIQUPEV3DW4Z76O0AS7GVSP";
            net.nurigo.java_sdk.api.Message coolsms = new net.nurigo.java_sdk.api.Message(api_key, api_secret);
            HashMap<String, String> params = new HashMap<String, String>();
    
           
            params.put("to", phoneNum);
            params.put("from", "01036769550" );
            params.put("type", "SMS");
            params.put("text", "인증번호는["+certificationNum+"]입니다.");
            params.put("app_version", "test app 1.2");
           
            try {
                JSONObject obj = (JSONObject) coolsms.send(params);
                System.out.println(obj.toString());
            } catch (CoolsmsException e) {
                System.out.println(e.getMessage());
                System.out.println(e.getCode());
            }
        }
}
