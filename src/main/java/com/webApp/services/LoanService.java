package com.webApp.services;

import com.webApp.dao.LoanDao;
import com.webApp.entity.Loan;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LoanService {
    private final LoanDao loanDao;
    private List<Loan> loans;

    @Setter
    private Loan loan;

    public LoanService(LoanDao loanDao) {
        this.loanDao = loanDao;
    }


    public List<Loan> showLoan(Long id) {
        loans = loanDao.showSelectedCredit(id);
        return loans;
    }


    public Loan showAllAmount(Double amount) {
        loan = new Loan();
        for (Loan value : loans) {
            if (value.getAmount() == amount) {
                loan = value;
            }
        }
        return loan;
    }


    public void saveNewLoan(Loan loan, Long id) {
        double interest = (loan.getAmount() * loan.getInterestRate() / 100);
        loan.setMonthlyPayment((loan.getAmount() + interest) / loan.getCreditTerm());
        loanDao.saveNewLoan(loan, id);
    }

    public void loanRepayment() {
        if (loan.getAmount() - loan.getMonthlyPayment() <= 0) {
            loanDao.deleteLoan(loan);
        } else {
            loanDao.loanRepayment(loan.getAmount() - loan.getMonthlyPayment(), loan.getLoanId());
        }
    }
}

