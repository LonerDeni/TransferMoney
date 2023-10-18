package com.example.transfermoney.rubbish;

import org.springframework.stereotype.Component;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Component //TODO удалить
@ConfigurationProperties(prefix = "currency")
public class AppConfig {

    private Integer value;

    public Integer getValue() {
        return value;
    }
}