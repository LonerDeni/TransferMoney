package com.example.transfermoney.model;

import lombok.Builder;

@Builder
public class ResponseTransfer {
    private String operationId;

    public ResponseTransfer(String operationId) {
        this.operationId = operationId;
    }

    public String getOperationId() {
        return operationId;
    }


    public void setOperationIdId(String operationId) {
        this.operationId = operationId;
    }

}
