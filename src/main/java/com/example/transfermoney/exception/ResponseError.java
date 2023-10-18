package com.example.transfermoney.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ResponseError {
    private Integer id;
    private String message;

    public ResponseError(Integer id, String message) {
        this.id = id;
        this.message = message;
    }

    public Integer getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}