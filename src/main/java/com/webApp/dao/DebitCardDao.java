package com.webApp.dao;

import com.webApp.entity.DebitCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;


@Component
public class DebitCardDao extends ConnectionToDb {
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private final UserDao userDao;


    @Autowired
    public DebitCardDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<DebitCard> showAllCard() {
        List<DebitCard> debitCards = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT cardNumber FROM CARD where user_id=?");
            preparedStatement.setLong(1, userDao.getCustomer().getId());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DebitCard debitCard = new DebitCard();
                debitCard.setCardNumber(resultSet.getString("cardNumber"));
                debitCards.add(debitCard);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return debitCards;
    }


    public void save (DebitCard debitCard) {
        double currentBalance = 0;
        SecureRandom random = new SecureRandom();
        String cardNumber = random.ints(48, 90)
                .filter(i -> (i < 57 || i > 65))
                .mapToObj(i -> (char) i)
                .limit(16)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
        int cvv = random.nextInt(999 - 100 + 1) + 100;
        Date customer = Date.valueOf(debitCard.getTerm().toString());
        try {
            preparedStatement = connection.prepareStatement("insert into card values (?,?,?,?,?,?,?)");
            preparedStatement.setDouble(1, currentBalance);
            preparedStatement.setString(2, cardNumber);
            preparedStatement.setInt(3, cvv);
            preparedStatement.setString(4, debitCard.getFirstName());
            preparedStatement.setString(5, debitCard.getLastName());
            preparedStatement.setDate(6,customer);
            preparedStatement.setLong(7, userDao.getCustomer().getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    public DebitCard showSelectedCard(String card) {
        DebitCard debitCard = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM CARD where cardNumber=?");
            preparedStatement.setString(1, card);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Date date = resultSet.getDate("term");
                LocalDate term = date.toLocalDate();

                debitCard = new DebitCard();
                debitCard.setCurrentBalance(resultSet.getDouble("currentBalance"));
                debitCard.setCardNumber(resultSet.getString("cardNumber"));
                debitCard.setCVV(resultSet.getInt("CVV"));
                debitCard.setFirstName(resultSet.getString("firstName"));
                debitCard.setLastName(resultSet.getString("lastName"));
                debitCard.setTerm(term);
                debitCard.setUserId(userDao.getCustomer());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return debitCard;
    }


}