package com.webApp.dao;

import com.webApp.entity.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class LoanDao extends ConnectionToDb {
    private final UserDao userDao;
    private PreparedStatement preparedStatement;

    @Autowired
    public LoanDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<Loan> showAllLoans() {
        List<Loan> loans = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("select amount from loan where user_id=?");
            preparedStatement.setLong(1, userDao.getCustomer().getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Loan loan = new Loan();
                loan.setAmount(resultSet.getDouble("amount"));
                loans.add(loan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loans;
    }



    public Loan showSelectedCredit(double loanAmount) {
        Loan loan = null;
        try {
            preparedStatement = connection.prepareStatement("select * from loan where amount=?");
            preparedStatement.setDouble(1, loanAmount);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                loan = new Loan();
                loan.setAmount(resultSet.getDouble("amount"));
                loan.setInterestRate(resultSet.getDouble("interestRate"));
                loan.setCreditTerm(resultSet.getInt("creditTerm"));
                loan.setMonthlyPayment(resultSet.getDouble("monthlyPayment"));
                loan.setDateOfRegistration(resultSet.getDate("dateOfRegistration").toLocalDate());
                loan.setUserId(userDao.getCustomer());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loan;
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
