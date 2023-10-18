package com.example.transfermoney.model;

public class ExistingCard {
    private String cardNumber;
    private String cardCVV;
    private String cardTill;
    private String cardCurrency;

    public ExistingCard(String cardNumber, String cardCVV, String cardTill, String cardCurrency) {
        this.cardNumber = cardNumber;
        this.cardCVV = cardCVV;
        this.cardTill = cardTill;
        this.cardCurrency = cardCurrency;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardCVV() {
        return cardCVV;
    }

    public String getCardTill() {
        return cardTill;
    }

    public String getCardCurrency() {
        return cardCurrency;
    }
}
