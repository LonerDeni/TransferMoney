package com.example.transfermoney;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@SpringBootApplication
public class TransferMoneyApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(TransferMoneyApplication.class, args);
    }
}
