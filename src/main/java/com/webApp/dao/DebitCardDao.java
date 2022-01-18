package com.webApp.dao;

import com.webApp.entity.DebitCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
public class DebitCardDao {

    private final UserDao userDao;


    @Autowired
    public DebitCardDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<DebitCard> showAllCardNumberWhereUserId() {
        return null;
    }


    public void saveNewCard(DebitCard debitCard) {

    }


    public DebitCard showSelectedCard(String card) {
        return null;
    }


}