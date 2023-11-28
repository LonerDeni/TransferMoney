package com.example.transfermoney.model;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


public class Amount {
    @NotNull(message = "Не задана сумма")
    @Min(value = 0,message = "Сумма перевода не может быть меньше или равна нулю")
    private Double value;
    @NotNull(message = "Не задана валюта")
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

    @Override
    public String toString() {
        return value.toString();
    }
}