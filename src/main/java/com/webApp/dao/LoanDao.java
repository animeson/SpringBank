package com.webApp.dao;

import com.webApp.entity.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class LoanDao {
    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public LoanDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Double> showAllLoans(long id) {
        return jdbcTemplate.queryForList("select amount from loan where user_id = ?", Double.class, id);
    }


    public Loan showSelectedCredit(double loanAmount) {
        return jdbcTemplate.query("select * from loan where amount =?", new BeanPropertyRowMapper<>(Loan.class), loanAmount).
                stream().findAny().orElse(null);
    }


    public void saveNewLoan(Loan newLoan, Long id) {

        LocalDate loanDate = LocalDate.now();
        double monthlyPayment = (newLoan.getAmount() / newLoan.getCreditTerm()) * newLoan.getInterestRate();

        jdbcTemplate.update("insert into loan values (?,?,?,?,?,?,?)",
                loanDate,
                newLoan.getInterestRate(),
                newLoan.getCreditTerm(),
                monthlyPayment,
                id,
                newLoan.getDescription(),
                newLoan.getAmount());

    }
}
