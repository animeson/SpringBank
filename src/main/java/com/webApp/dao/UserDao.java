package com.webApp.dao;

import com.webApp.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDao {
   private final List<User> users;

    {
        users = new ArrayList<>();
        users.add(new User("Sasha","Svistun","sasha","1",null));
    }
    public List<User> users(){
        return users;
    }


}
