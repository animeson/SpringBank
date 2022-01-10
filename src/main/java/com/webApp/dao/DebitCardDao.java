package com.webApp.dao;

import com.webApp.entity.DebitCard;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Component
public class DebitCardDao extends ConnectionToDb {
    private List<DebitCard> debitCards;

    public List<DebitCard> showAllCard() {
        debitCards = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM CARD");
            while (resultSet.next()) {
                DebitCard debitCard = new DebitCard();
                debitCard.setCurrentBalance(resultSet.getDouble("currentBalance"));
                debitCard.setCardNumber(resultSet.getString("cardNumber"));
                debitCard.setCVV(resultSet.getInt("CVV"));
                debitCard.setFirstName(resultSet.getString("firstName"));
                debitCard.setLastName(resultSet.getString("lastName"));
                debitCard.setTerm(new SimpleDateFormat(resultSet.getDate("term")));

                debitCards.add(debitCard);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return debitCards;
    }


/*    public void save(DebitCard debitCard) {
        double currentBalance = 0;
        SecureRandom random = new SecureRandom();
        String cardNumber = random.ints(48, 90)
                .filter(i -> (i < 57 || i > 65))
                .mapToObj(i -> (char) i)
                .limit(16)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
        int CVV = random.nextInt(999 - 100 + 1) + 100;
        debitCards.add(new DebitCard(currentBalance, cardNumber, CVV, debitCard.getFirstName(), debitCard.getLastName(),
                debitCard.getTerm()));

    }*/


    public DebitCard showSelectedCard(String card) {
        return debitCards.stream().filter(debitCard -> debitCard.getCardNumber().equals(card)).findAny().orElse(null);
    }


}
