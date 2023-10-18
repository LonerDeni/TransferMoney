package com.example.transfermoney.controller;

import com.example.transfermoney.model.Confirm;
import com.example.transfermoney.model.PayInfo;
import com.example.transfermoney.model.ResponseTransfer;
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
    //ConfirmService confirmService;


    public TransferController(TransferService service) {
        this.service = service;
    }


    @PostMapping("/transfer")
    public ResponseTransfer getAuthorities(@Valid @RequestBody PayInfo payInfo) {
        return service.transferM(payInfo);
    }

    @PostMapping("/confirmOperation")
    public ResponseTransfer getAuthorities(@Valid @RequestBody Confirm confirm) {
        return service.confirmPay(confirm);
    }
}