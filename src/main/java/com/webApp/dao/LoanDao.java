package com.webApp.dao;

import com.webApp.entity.Loan;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class LoanDao extends ConnectionToDb {
    private List<Loan> loans;

    public List<Loan> showAllLoans() {
        loans = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM LOANS");
            while (resultSet.next()) {
                Loan loan = new Loan();
                loan.setAmount(resultSet.getDouble("amount"));
                loan.setInterestRate(resultSet.getDouble("interestRate"));
                loan.setCreditTerm(resultSet.getInt("creditTerm"));
                loan.setMonthlyPayment(resultSet.getDouble("monthlyPayment"));
                /*            loan.setDateOfRegistration(resultSet.getDate("dateOfRegistration"));*/
                loans.add(loan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loans;
    }

    public Loan showSelectedCredit(double amount) {
        return loans.stream().filter(loan -> loan.getAmount() == amount).findAny().orElse(null);
    }

    /*public void save (Loan loan) {
        loans.add(new Loan(
                loan.getDateOfRegistration(),
                loan.getAmount(),
                loan.getInterestRate(),
                loan.getCreditTerm(),
                loan.getMonthlyPayment()));
    }*/


}
