package com.webApp.services;

import com.webApp.dao.LoanDao;
import com.webApp.entity.LoanEntity;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LoanService {
    private final LoanDao loanDao;
    private List<LoanEntity> loans;

    @Setter
    private LoanEntity loan;

    public LoanService(LoanDao loanDao) {
        this.loanDao = loanDao;
    }


    public List<LoanEntity> showLoan(Long id) {
        return null;
    }


    public LoanEntity showAllAmount(Double amount) {
        return null;
    }


    public void saveNewLoan(LoanEntity loan, Long id) {

    }

    public void loanRepayment() {

    }
}

