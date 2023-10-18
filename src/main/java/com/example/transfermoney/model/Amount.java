package com.example.transfermoney.model;

import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class Amount {

    private Double value;

    private String currency;

    public Amount(Double value, String currency) {
        this.value = value;
        this.currency = currency;
    }
    public Amount(String currency) {
        this.currency = currency;
    }

    public Double getValue() {
        return value;
    }

    public String getCurrency() {
        return currency;
    }
}