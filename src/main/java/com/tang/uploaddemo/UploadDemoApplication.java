package com.tang.uploaddemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;

@SpringBootApplication(exclude = MultipartAutoConfiguration.class)
public class UploadDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(UploadDemoApplication.class, args);
    }

}
