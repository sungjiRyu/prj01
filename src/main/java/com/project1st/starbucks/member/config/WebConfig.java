package com.project1st.starbucks.member.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("*"); // 접근 권한 설정

        // addmapping 접근할수있는 url
        // allowdOrigins 접근할 수 있는 아이피
        // allowdMethods 접근할 수 있는 get, post...
    }
    
}

