package com.webApp.dao;

import com.webApp.entity.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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

    public List<Loan> showSelectedCredit(Long id) {
        return jdbcTemplate.query("select loandate,interestrate,creditterm,monthlypayment,description, loan_id ,amount from loan where user_id =?",
                new BeanPropertyRowMapper<>(Loan.class), id);
    }


    public void saveNewLoan(Loan newLoan, Long id) {
        jdbcTemplate.update("insert into loan (loandate, interestrate, creditterm, monthlypayment, description, amount, user_id) values  (?,?,?,?,?,?,?)",
                newLoan.getLoanDate(),
                newLoan.getInterestRate(),
                newLoan.getCreditTerm(),
                newLoan.getMonthlyPayment(),
                newLoan.getDescription(),
                newLoan.getAmount(),
                id);
    }

    public void loanRepayment(Double newAmount, Long loam_id) {
        jdbcTemplate.update("update loan set amount =? where loan_id = ?", newAmount, loam_id);
    }

    public void deleteLoan (Loan loan_id) {
        jdbcTemplate.update("delete from loan where loan_id =?", loan_id.getLoanId());
    }

}
