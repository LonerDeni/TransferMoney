package com.example.transfermoney.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TransactionInfo {
    private String uuid;
    private String cardFromNumber;

    private String cardToNumber;

    private Double sumTransfer;

    private boolean confirmed;

    @Override
    public String toString() {
        return "TransactionInfo{" +
                "cardFromNumber='" + cardFromNumber + '\'' +
                ", cardToNumber='" + cardToNumber + '\'' +
                ", sumTransfer=" + sumTransfer +
                ", confirmed=" + confirmed +
                '}';
    }
}
