package com.webApp.services;

import com.webApp.dao.LoanDao;
import com.webApp.entity.Loan;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Objects;


@Service
public class LoanService {
    private final LoanDao loanDao;

    public LoanService(LoanDao loanDao, ClientService clientService) {
        this.loanDao = loanDao;
        this.clientService = clientService;
    }

    private final ClientService clientService;


    public Loan showAllAmount(Double amount) {
        for (Loan value : clientService.getUser().getLoansById()) {
            if (Objects.equals(value.getAmount(), amount)) {
                return value;
            }
        }
        throw new NoSuchElementException();
    }


    public void saveNewLoan(Loan loan, Long clientId) {
        double interest = (loan.getAmount() * loan.getInterestRate() / 100);
        loan.setMonthlyPayment((loan.getAmount() + interest) / loan.getCreditTerm());
        loan.setLoanDate(LocalDate.now());
        loan.setClientId(clientId);
        loanDao.saveNewLoan(loan);

    }

    public void loanRepayment() {

    }
}

