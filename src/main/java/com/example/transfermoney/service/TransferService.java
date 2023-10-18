package com.example.transfermoney.service;

import com.example.transfermoney.model.Confirm;
import com.example.transfermoney.model.PayInfo;
import com.example.transfermoney.model.ResponseTransfer;
import com.example.transfermoney.repository.TransferRepository;
import org.springframework.stereotype.Service;

@Service
public class TransferService {
    private final TransferRepository transferRepositoryServ;

    public TransferService(TransferRepository transferRepositoryServ) {
        this.transferRepositoryServ = transferRepositoryServ;
    }

    public ResponseTransfer transferM(PayInfo pay) {
        return transferRepositoryServ.transferMoney(pay);
    }

    public ResponseTransfer confirmPay(Confirm confirm) {
        return transferRepositoryServ.checkPayValid(confirm);
    }
}
