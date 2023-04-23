package com.zerobase.minicommerce.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Value("${uploadPath}")
    String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 이미지 data url_path 를 view(html) 에서 출력 설정
        // addResourceHandler - URL path 지정 : 위의 설정 대로 했을 경우 localhost8080/img 와 같이 됩니다.
        // addResourceLocations - 이미지가 업로드 될 실제 경로, 윈도우 시스템의 경우 'file:///경로' 형태로 사용
        registry.addResourceHandler("/img/**")
                .addResourceLocations(uploadPath);
//        registry.addResourceHandler("/files/**").addResourceLocations("file:///C:/study/dev/spring/fastlms/files/");

    }
}
