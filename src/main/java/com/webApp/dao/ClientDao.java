package com.webApp.dao;

import com.webApp.entity.ClientEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClientDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public ClientDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public ClientEntity showSelectedUser(String email, String password) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from ClientEntity");
    }
 /*

  public void newUser(User user) {

    }


    public void edit(long id, User updateUser) {

    }

    public void editPassword(User user) {

    }

*/

}