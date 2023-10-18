package com.example.transfermoney.errors;

import com.example.transfermoney.model.ResponseTransfer;

public class InvalidData extends RuntimeException {
    public InvalidData(String msg) {
        super(msg);
    }
}
