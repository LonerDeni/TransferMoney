package com.example.transfermoney.model;

import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class PayInfo {
    @Pattern(regexp = "^[0-9]{16}$",
            message = "Номер карты отправителя дожен состоять из 16 цифр")
    @NotNull(message = "Номер карты отправителя не указан")
    private String cardFromNumber;
    @DateTimeFormat(pattern="MM/yy")
    @NotNull(message = "Cрок действия карты не указан")
    private String cardFromValidTill;
    @Pattern(regexp = "^[0-9]{3}$",
            message = "ccv должен состоять из 3 цифр")
    @NotNull(message = "Не указан некорректный cvv")
    private String cardFromCVV;

    @Getter
    @Pattern(regexp = "^[0-9]{16}$",
            message = "Номер карты получателя дожен состоять из 16 цифр")
    @NotNull(message = "Не указан номер карты получателя")
    private String cardToNumber;
    @NotNull( message = "Данные по сумме и валюте не заданы")
    public Amount amount;

    public PayInfo(String cardFromNumber, String cardFromValidTill, String cardFromCVV, String cardToNumber, Amount amount) {
        this.cardFromNumber = cardFromNumber;
        this.cardFromValidTill = cardFromValidTill;
        this.cardFromCVV = cardFromCVV;
        this.cardToNumber = cardToNumber;
        this.amount = amount;
    }

    public PayInfo() {
    }

    public String getCardFromNumber() {
        return cardFromNumber;
    }

    public String getCardFromValidTill() {
        return cardFromValidTill;
    }

    public String getCardFromCVV() {
        return cardFromCVV;
    }

    @Override
    public String toString() {
        return "PayInfo{" +
                "cardFromNumber='" + cardFromNumber + '\'' +
                ", cardToNumber='" + cardToNumber + '\'' +
                ", amount=" + amount +
                '}';
    }
}