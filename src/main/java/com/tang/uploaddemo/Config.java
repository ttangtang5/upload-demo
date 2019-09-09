package com.tang.uploaddemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;

/**
 * @Description
 * @Author tang
 * @Date 2019-06-18 23:14
 * @Version 1.0
 **/
@Configuration
public class Config {

    @Bean
    public MultipartResolver customMultipartResolver() {
        return new CustomMultipartResolver();
    }
}
