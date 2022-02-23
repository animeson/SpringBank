package com.webApp.dao;

import com.webApp.entity.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ClientDao {
    Session session = null;
    Transaction transaction = null;
    private final SessionFactory sessionFactory;

    public ClientDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Client showSelectedUser(String email, String password) {
        return (Client) sessionFactory.getCurrentSession().
                createQuery("from Client where email = :email and password = :password")
                .setParameter("email", email).setParameter("password", password).uniqueResult();


    }


    public void newUser(Client user) {

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
    }


    public void edit(Client updateUser) {
        sessionFactory.getCurrentSession().update(updateUser);

    }

    public Client searchUserById(Long id) {
        return (Client) sessionFactory.getCurrentSession().
                createQuery("from Client where id=: id").setParameter("id", id).uniqueResult();
    }

    public Client searchUser(String email) {
        return (Client) sessionFactory.getCurrentSession().
                createQuery("from Client where email = :email")
                .setParameter("email", email).uniqueResult();
    }


    public void editPassword(Client user) {
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }

    }


}

