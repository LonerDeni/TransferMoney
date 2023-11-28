package com.example.transfermoney.service;

import com.example.transfermoney.AllCurrency;
import com.example.transfermoney.errors.InvalidData;
import com.example.transfermoney.logger.Logger;
import com.example.transfermoney.logger.LoggerImpl;
import com.example.transfermoney.model.*;
import com.example.transfermoney.repository.CardRepository;
import com.example.transfermoney.repository.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransferService {

    private final TransferRepository transferRepositoryServ;
    private final CardRepository cardRepository;
    private static Logger logger = LoggerImpl.getInstance();

    public ResponseTransfer transferMoney(PayInfo pay) {
        double sumFinalTo = existing(pay);
        logger.info(pay.toString());
        return transferRepositoryServ.transferMoney(pay, sumFinalTo);
    }

    public TransactionInfo confirmPay(Confirm confirm) {
        Optional<TransactionInfo> transactionInfo = transferRepositoryServ.getTransactionByUUID(confirm.getOperationId());
        if(transactionInfo.isEmpty()){
            logger.error("Операция не найдена" + transactionInfo);
            throw new InvalidData("Данная операция не найдена");
        }
        if (!confirm.getCode().equals("1234")) {
            logger.error("Неверный код подтверждения" + transactionInfo);
            throw new InvalidData("Неверный код подтверждения");
        }
        TransactionInfo transaction = transactionInfo.get();
        if(transaction.isConfirmed()){
            logger.error("Транзакция уже подтверждена" + transactionInfo);
            throw new InvalidData("Операция подтверждена");
        }
        transaction.setConfirmed(true);
        logger.info("Транзакция подтверждена" + transactionInfo);
        return transferRepositoryServ.save(transaction);
    }

    public String addCard(CreditCard creditCard){
        cardRepository.addNewCard(creditCard);
        return cardRepository.addNewCard(creditCard);
    }

    public Double existing(PayInfo payInfo) {
        Optional<ExistingCard> optionalCard = cardRepository.getCardByCardNumber(payInfo.getCardToNumber());
        if(optionalCard.isEmpty()){
            logger.error("Номер карты не существует" + payInfo);
            throw new InvalidData(String.format("Карты с номером [%s] не существует", payInfo.getCardToNumber()));
        }
        if(!optionalCard.get().getCardCVV().equals(payInfo.getCardFromCVV())){
            logger.error("Некорректный CVV код" + payInfo);
            throw new InvalidData("Некорректный CVV код");
        }
        if(!optionalCard.get().getCardTill().equals(payInfo.getCardFromValidTill())){
            logger.error("Некрректный срок действия карты" + payInfo);
            throw new InvalidData("Некрректный срок действия карты (MM/YY)");
        }
        if(payInfo.getCardToNumber().equals(payInfo.getCardFromNumber())){
            logger.error("Номер получателя совпадает с номером отправителя" + payInfo);
            throw new InvalidData("Номер получателя совпадает с номером отправителя");
        }
        String currencyTo = optionalCard.get().getCardCurrency();
        String currencyFrom = payInfo.amount.getCurrency();
        if (!currencyFrom.equals(currencyTo)) {
            logger.info("Рассчитали сумму перевода в валюте" + payInfo);
            return payInfo.amount.getValue() * AllCurrency.getSumCurrency(payInfo.amount.getCurrency());
        }
        return payInfo.amount.getValue();
    }
}
