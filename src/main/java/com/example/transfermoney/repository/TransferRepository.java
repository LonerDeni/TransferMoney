package com.example.transfermoney.repository;

import com.example.transfermoney.logger.Logger;
import com.example.transfermoney.logger.LoggerImpl;
import com.example.transfermoney.model.*;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TransferRepository {
    private final Map<String, TransactionInfo> allTransferInfo = new HashMap<>();
    private static Logger logger = LoggerImpl.getInstance();


    public ResponseTransfer transferMoney(PayInfo payInfo, double sum) {
        String uniqueID = UUID.randomUUID().toString();
        allTransferInfo.put(uniqueID, new TransactionInfo(uniqueID, payInfo.getCardFromNumber(), payInfo.getCardToNumber(), sum, false));
        logger.info("Операция по перерводу денег проведена" + payInfo);
        return new ResponseTransfer(uniqueID);
    }

    public TransactionInfo save(TransactionInfo transactionInfo) {
        logger.info("Транзакция проведена" + transactionInfo);
        allTransferInfo.put(transactionInfo.getUuid(), transactionInfo);
        return transactionInfo;
    }

    public Optional<TransactionInfo> getTransactionByUUID(String uuid) {
        if (allTransferInfo.containsKey(uuid)) {
            return Optional.of(allTransferInfo.get(uuid));
        } else {
            return Optional.empty();
        }
    }
}