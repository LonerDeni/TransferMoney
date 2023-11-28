package com.example.transfermoney.controller;

import com.example.transfermoney.model.*;
import com.example.transfermoney.service.TransferService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
public class TransferController {

    TransferService service;


    public TransferController(TransferService service) {
        this.service = service;
    }


    @PostMapping("/transfer")
    public ResponseTransfer addTransfer(@Valid @RequestBody PayInfo payInfo) {
        return service.transferMoney(payInfo);
    }

    @PostMapping("/confirmOperation")
    public ResponseTransfer checkOperation(@Valid @RequestBody Confirm confirm) {
        TransactionInfo transactionInfo = service.confirmPay(confirm);
        return ResponseTransfer.builder().operationId(transactionInfo.getUuid()).build();
    }
    @PostMapping("/addCard")
    public String addCard(@Valid @RequestBody CreditCard creditCardCard) {
        return service.addCard(creditCardCard);
    }
}