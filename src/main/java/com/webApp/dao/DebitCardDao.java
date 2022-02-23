package com.webApp.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import com.webApp.entity.*;
import javax.transaction.Transactional;


@Repository
@Transactional
public class DebitCardDao {
    private final SessionFactory sessionFactory;

    public DebitCardDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveNewCard(DebitCard debitCard) {
        sessionFactory.getCurrentSession().save(debitCard);
    }


    public void deleteDebitCard(DebitCard debitCard) {
        sessionFactory.getCurrentSession().delete(debitCard);
    }


    public void remittance(Double replenishmentAmount, String sender, String recipient) {


    }
}