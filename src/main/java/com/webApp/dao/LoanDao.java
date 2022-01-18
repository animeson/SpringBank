package com.webApp.dao;

import com.webApp.entity.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoanDao {
    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public LoanDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Loan> showAllLoans() {
        return null;
    }


    public Loan showSelectedCredit(double loanAmount) {
        return null;
    }


    public void save(Loan loan) {

    }


}
