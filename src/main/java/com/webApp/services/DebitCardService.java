package com.webApp.services;

import com.webApp.dao.DebitCardDao;
import com.webApp.entity.Client;
import com.webApp.entity.DebitCard;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.NoSuchElementException;


@Component
@Transactional
public class DebitCardService {
    private final DebitCardDao debitCardDao;

    private final ClientService clientService;

    public DebitCardService(DebitCardDao debitCardDao, ClientService clientService) {
        this.debitCardDao = debitCardDao;
        this.clientService = clientService;
    }

    public DebitCard showSelectedCard(String card) {
        for (DebitCard value : clientService.getUser().getDebitCardsById()) {
            if (value.getCardNumber().equals(card)) {
                return value;
            }
        }
        throw new NoSuchElementException();
    }

    public void createNewCard(Client client) {
        SecureRandom random = new SecureRandom();
        String cardNumber = random.ints(48, 90)
                .filter(i -> (i < 57 || i > 65))
                .mapToObj(i -> (char) i)
                .limit(16)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();

        int CVV = random.nextInt(999 - 100 + 1) + 100;
        LocalDate term = LocalDate.now().plusYears(3);
        DebitCard debitCard = new DebitCard();
        debitCard.setCvv(CVV);
        debitCard.setCurrentBalance(0.0);
        debitCard.setCardNumber(cardNumber);
        debitCard.setTerm(term);
        debitCard.setFirstName(client.getFirstName());
        debitCard.setLastName(client.getLastName());
        debitCard.setClientId(client.getId());

        debitCardDao.saveNewCard(debitCard);


    }


    public void deleteDebitCard(String card) {
        for (DebitCard value : clientService.getUser().getDebitCardsById()) {
            if (value.getCardNumber().equals(card)) {
                debitCardDao.deleteDebitCard(value);

            }
        }
    }


    public void deleteAllDebitCard() {
        clientService.getUser().getDebitCardsById().forEach(debitCardDao::deleteDebitCard);
    }

    public void remittance(DebitCard modelData) {
        /*debitCardDao.remittance();*/

    }
}

