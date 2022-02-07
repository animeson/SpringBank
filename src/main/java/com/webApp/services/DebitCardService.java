package com.webApp.services;

import com.webApp.dao.DebitCardDao;
import com.webApp.entity.DebitCard;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.List;


@Component
public class DebitCardService {
    private final DebitCardDao debitCardDao;
    private List<DebitCard> debitCards;

    @Getter
    private DebitCard debitCard;

    @Autowired
    public DebitCardService(DebitCardDao debitCardDao) {
        this.debitCardDao = debitCardDao;

    }

    public List<DebitCard> showCard(Long id) {
        debitCards = debitCardDao.showSelectedCard(id);
        return debitCards;
    }


    public DebitCard showSelectedCard(String card) {
        debitCard = new DebitCard();
        for (DebitCard value : debitCards) {
            if (value.getCardNumber().equals(card)) {
                debitCard = value;
            }
        }
        return debitCard;
    }

    public void createNewCard(String firstName, String lastName, Long id) {
        SecureRandom random = new SecureRandom();
        String cardNumber = random.ints(48, 90)
                .filter(i -> (i < 57 || i > 65))
                .mapToObj(i -> (char) i)
                .limit(16)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();

        int cvv = random.nextInt(999 - 100 + 1) + 100;

        LocalDate term = LocalDate.now().plusYears(3);
        DebitCard debitCard = new DebitCard();
        debitCard.setCVV(cvv);
        debitCard.setCurrentBalance(0);
        debitCard.setCardNumber(cardNumber);
        debitCard.setTerm(term);
        debitCard.setFirstName(firstName);
        debitCard.setLastName(lastName);
        debitCardDao.saveNewCard(debitCard, id);
    }


    public void deleteDebitCard(String numberCard) {
        debitCardDao.deleteDebitCard(numberCard);
    }


    public void deleteAllDebitCard(Long id) {
        debitCardDao.deleteAllDebitCard(id);
    }

    public void remittance(DebitCard modelData) {
        if (modelData.getCurrentBalance() > debitCard.getCurrentBalance()) {
            modelData.setCurrentBalance(0.0);
        }
        debitCardDao.remittance(modelData.getCurrentBalance(), debitCard.getCardNumber(), modelData.getCardNumber());
    }
}

