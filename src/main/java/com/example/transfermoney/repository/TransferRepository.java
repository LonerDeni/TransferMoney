package com.example.transfermoney.repository;

import com.example.transfermoney.AllCurrency;
import com.example.transfermoney.errors.InvalidData;
import com.example.transfermoney.model.*;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class TransferRepository {
    List<String> currencys = List.of("Рубль", "Евро", "Доллар", "Юань", "Фунт");
    //private final AtomicInteger index = new AtomicInteger(0);
    private final List<ExistingCard> actualCard = new ArrayList<>();
    //private static ConcurrentMap<Integer, TransactionInfo> allTransInfo = new ConcurrentHashMap<>();
    private static Map<String, TransactionInfo> allTransInfo = new HashMap<>();

    public ResponseTransfer transferMoney(PayInfo payInfo) {
        addCard();
        String uniqueID = UUID.randomUUID().toString();
        if (isEmpty(payInfo.amount.getCurrency()) || payInfo.amount.getValue() == null) {
            throw new InvalidData("Не задана сумма и/или валюта");
        }
        //Проверяем существует ли такой номер карты с которой отправлеям
        if (checkCard(payInfo.getCardFromNumber()) == null) {
            throw new InvalidData("Указан не существующий номер карты отправителя");
        }
        //Проверяем существует ли такой номер карты на которую отправлеям
        if (checkCard(payInfo.getCardToNumber()) == null) {
            throw new InvalidData("Указан не существующий номер карты получателя");
        }
        //Проверяем корректный ли указан номер ccv карты
        if (!checkCard(payInfo.getCardFromNumber()).getCardCVV().equals(payInfo.getCardFromCVV())) {
            throw new InvalidData("Указан некорректный cvv");
        }
        //Проверяем корректная ли указана дата действия карты
        if (!checkCard(payInfo.getCardFromNumber()).getCardTill().equals(payInfo.getCardFromValidTill())) {
            throw new InvalidData("Указан некорректный срок действия карты");
        }
        //Проверяем поддерживается ли такая валюта
        if (!currencys.contains(payInfo.amount.getCurrency())) {
            throw new InvalidData("Указанная валюта не поддерживается");
        }
        if (payInfo.amount.getValue() <= 0) {
            throw new InvalidData("Сумма перевода не может быть меньше или равна нулю");
        }
        double sumFinalTo = existing(payInfo);
        //allTransInfo.put(index.incrementAndGet(), new TransactionInfo(payInfo.getCardFromNumber(), payInfo.getCardToNumber(), sumFinalTo));
        allTransInfo.put(uniqueID, new TransactionInfo(payInfo.getCardFromNumber(), payInfo.getCardToNumber(), sumFinalTo));
        return new ResponseTransfer(uniqueID);
        //return new ResponseTransfer(index.get(), String.format("На карту: %s переведена сумма %s - %s", payInfo.getCardFromNumber(), sumFinalTo, payInfo.amount.getCurrency()));
    }


    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public Double existing(PayInfo payInfo) {
        String currencyTo = actualCard
                .stream().filter(x -> x.getCardNumber().equals(payInfo.getCardToNumber()))
                .map(ExistingCard::getCardCurrency).collect(Collectors.toList()).get(0);
        String currencyFrom = payInfo.amount.getCurrency();
        if (!currencyFrom.equals(currencyTo)) {
            return payInfo.amount.getValue() * AllCurrency.getSumCurrency(payInfo.amount.getCurrency());
        }
        return payInfo.amount.getValue();
    }

    public void addCard() {
        actualCard.add(new ExistingCard("1111222233334444", "123", "04/24", "Рубль"));
        actualCard.add(new ExistingCard("1111222233335555", "125", "05/25", "Евро"));
        actualCard.add(new ExistingCard("1111222233336666", "126", "06/26", "Доллар"));
        actualCard.add(new ExistingCard("1111222233337777", "127", "07/27", "Фунт"));
        actualCard.add(new ExistingCard("1111222233338888", "128", "08/28", "Юань"));
    }

    public ExistingCard checkCard(String numCard) {
        for (ExistingCard s : actualCard) {
            if (s.getCardNumber().equals(numCard))
                return s;
        }
        return null;
    }

    public ResponseTransfer checkPayValid(Confirm confirm) {
        if (!allTransInfo.containsKey(confirm.getOperationId())) {
            throw new InvalidData("Данная операция не найдена");
        }
        if (!confirm.getCode().equals("1234")) {
            throw new InvalidData("Неверный код подтверждения");
        }
        return new ResponseTransfer("Succes operation");
    }
}