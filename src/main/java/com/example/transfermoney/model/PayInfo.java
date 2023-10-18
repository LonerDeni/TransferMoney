package com.example.transfermoney.model;

import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;
import javax.validation.constraints.Pattern;

@Repository
public class PayInfo {
    @Pattern(regexp = "^[0-9]{16}$",
            message = "Номер карты отправителя дожен состоять из 16 цифр")
    private String cardFromNumber;

//    @Pattern(regexp = "^(0[0-1]|1[1-9])\\/?([0-9]{4}|[0-9]{2})$",
//            message = "Неверный формат даты. Введите дату в формате мм/гг")
    @DateTimeFormat(pattern="MM/yy")
    private String cardFromValidTill;
    @Pattern(regexp = "^[0-9]{3}$",
            message = "ccv должен состоять из 3 цифр")
    private String cardFromCVV;
    @Getter
    @Pattern(regexp = "^[0-9]{16}$",
            message = "Номер карты получателя дожен состоять из 16 цифр")
    private String cardToNumber;
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

}