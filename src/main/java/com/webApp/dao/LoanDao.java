package com.webApp.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import com.webApp.entity.*;

import javax.transaction.Transactional;

@Repository
@Transactional
public class LoanDao {
    private final SessionFactory sessionFactory;

    public LoanDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public void saveNewLoan(Loan newLoan) {
        sessionFactory.getCurrentSession().save(newLoan);
    }

    public void loanRepayment(Double newAmount, Long loam_id) {

    }

    public void deleteLoan(Loan loan_id) {

    }
}
