package com.example.transfermoney.repository;

import com.example.transfermoney.logger.Logger;
import com.example.transfermoney.logger.LoggerImpl;
import com.example.transfermoney.model.CreditCard;
import com.example.transfermoney.model.ExistingCard;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class CardRepository {
    private final Map<String, ExistingCard> cardMap = new HashMap<>();
    private static Logger logger = LoggerImpl.getInstance();

    public Optional<ExistingCard> getCardByCardNumber(String cardNumber){
        if(cardMap.containsKey(cardNumber)){
            logger.info("Получили номер карты");
            return Optional.of(cardMap.get(cardNumber));
        } else {
            logger.info("Номер карты не найден");
            return Optional.empty();
        }
    }

    public String addNewCard(CreditCard creditCard){
        logger.info("Добавили новую карту");
        cardMap.put(creditCard.getCardFromNumber(),new ExistingCard(creditCard.getCardFromNumber(),creditCard.getCardFromCVV(),creditCard.getCardFromValidTill(),creditCard.getCurrency()));
        return "Карта добавлена";
    }


    @PostConstruct
    void addCard() {
        cardMap.put("1111222233334444",new ExistingCard("1111222233334444", "123", "04/24", "Рубль"));
        cardMap.put("1111222233335555",new ExistingCard("1111222233335555", "125", "05/25", "Евро"));
        cardMap.put("1111222233336666",new ExistingCard("1111222233336666", "126", "06/26", "Доллар"));
        cardMap.put("1111222233337777",new ExistingCard("1111222233337777", "127", "07/27", "Фунт"));
        cardMap.put("1111222233338888",new ExistingCard("1111222233338888", "128", "08/28", "Юань"));
    }
}
