package com.example.transfermoney.model;

public class TransactionInfo {
    private String cardFromNumber;
    private String cardToNumber;

    private Double sumTransfer;
    private String currencyTransfer;

    public TransactionInfo(String cardFromNumber, String cardToNumber, Double sumTransfer) {
        this.cardFromNumber = cardFromNumber;
        this.cardToNumber = cardToNumber;
        this.sumTransfer = sumTransfer;
    }

    public String getCardFromNumber() {
        return cardFromNumber;
    }

    public String getCardToNumber() {
        return cardToNumber;
    }

    public Double getSumTransfer() {
        return sumTransfer;
    }

    public String getCurrencyTransfer() {
        return currencyTransfer;
    }
}
