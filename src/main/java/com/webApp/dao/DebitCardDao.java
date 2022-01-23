package com.webApp.dao;

import com.webApp.entity.DebitCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.List;


@Component
public class DebitCardDao {
    private final JdbcTemplate jdbcTemplate;
    private List<String> list;

    @Autowired
    public DebitCardDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }

    public List<String> getList() {
        return list;
    }

    public List<String> showAllCardNumberWhereUserId(long userID) {
        list = jdbcTemplate.queryForList("select cardnumber from card where user_id = ?",
                String.class, userID);
        return list;
    }


    public DebitCard showSelectedCard(String card) {
        return jdbcTemplate.query("select * from card where cardnumber =?",
                        new BeanPropertyRowMapper<>(DebitCard.class), card).
                stream().findAny().orElse(null);
    }


    public void saveNewCard(DebitCard debitCard, long userID) {
        SecureRandom random = new SecureRandom();

        double currentBalance = 0; // по умолчанию денег нет

        String cardNumber = random.ints(48, 90)
                .filter(i -> (i < 57 || i > 65))
                .mapToObj(i -> (char) i)
                .limit(16)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append) // рандомим номер карты
                .toString();

        int cvv = random.nextInt(999 - 100 + 1) + 100; // рандомим cvv

        LocalDate term = LocalDate.now().plusYears(3); //срок действия карты равен 3 года

        jdbcTemplate.update("insert into card values (?,?,?,?,?,?,?)",
                currentBalance,
                cardNumber,
                cvv,
                debitCard.getFirstName(),
                debitCard.getLastName(),
                term,
                userID);
    }


    public void deleteDebitCard(String cardNumber) {
        jdbcTemplate.update("delete from card where cardnumber =?", cardNumber);
    }


}