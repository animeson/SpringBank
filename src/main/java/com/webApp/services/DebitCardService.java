package com.webApp.services;

import com.webApp.entity.DebitCardEntity;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class DebitCardService {

    public List<DebitCardEntity> showCard(Long id) {
        return null;
    }


    public DebitCardEntity showSelectedCard(String card) {
return null;
    }

    public void createNewCard(String firstName, String lastName, Long id) {
/*        SecureRandom random = new SecureRandom();
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
        debitCardDao.saveNewCard(debitCard, id);*/
    }


/*    public void deleteDebitCard(String numberCard) {
        debitCardDao.deleteDebitCard(numberCard);
    }


    public void deleteAllDebitCard(Long id) {

    }

    public void remittance(DebitCardEntity modelData) {

    }*/
}

