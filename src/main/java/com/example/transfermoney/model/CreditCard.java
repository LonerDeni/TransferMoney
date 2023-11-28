package com.example.transfermoney.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
@RequiredArgsConstructor
@Getter
public class CreditCard {
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

    @NotNull(message = "Не задана валюта")
    private String currency;
}
