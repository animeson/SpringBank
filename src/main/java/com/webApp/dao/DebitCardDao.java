package com.webApp.dao;

import com.webApp.entity.DebitCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class DebitCardDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DebitCardDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<DebitCard> showSelectedCard(Long id) {
        return jdbcTemplate.query("select currentbalance, cardnumber, cvv, firstname, lastname, term  from card where user_id =?",
                new BeanPropertyRowMapper<>(DebitCard.class),id);
    }


    public void saveNewCard (DebitCard debitCard, Long id) {
        jdbcTemplate.update("insert into card values (?,?,?,?,?,?,?)",
                debitCard.getCurrentBalance(),
                debitCard.getCardNumber(),
                debitCard.getCVV(),
                debitCard.getFirstName(),
                debitCard.getLastName(),
                debitCard.getTerm(),
                id);
    }


    public void deleteDebitCard(String cardNumber) {
        jdbcTemplate.update("delete from card where cardnumber =?", cardNumber);
    }


    public void deleteAllDebitCard(Long id) {
        jdbcTemplate.update("delete from card where user_id =?", id);
    }


    public void remittance (Double replenishmentAmount, String sender, String recipient) {
        jdbcTemplate.update("update card set currentbalance = currentbalance - ? where cardnumber  = ?", replenishmentAmount, sender);
        jdbcTemplate.update("update card set currentbalance = currentbalance + ? where cardnumber  = ?", replenishmentAmount, recipient);

    }
}