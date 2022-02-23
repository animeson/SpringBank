package com.webApp.dao;

import com.webApp.entity.Code;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CodeDao {


    private final SessionFactory sessionFactory;

    public CodeDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void send(Code code) {
        sessionFactory.getCurrentSession().save(code);
    }

    public Code search(String code) {
        return (Code) sessionFactory.getCurrentSession().createQuery("from Code where code =: code")
                .setParameter("code", code).uniqueResult();
    }

    public void del(Long clientId) {
        Code code = (Code) sessionFactory.getCurrentSession().createQuery("from Code where clientByUserId.id =:id")
                .setParameter("id", clientId).uniqueResult();
        sessionFactory.getCurrentSession().delete(code);
    }
}
