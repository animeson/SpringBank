package com.webApp.dao;

import com.webApp.entity.User;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDao extends ConnectionToDb {
    private User customer;
    private List<User> users;

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public List<User> showAllUsers() {

        users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM USERS");

            while (resultSet.next()) {
                User user = new User();
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                /*    user.setRegistrationDate(resultSet.getDate("registrationDate"));*/
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

/*
    public void save (User user){
        users.add(user);

    }*/

    public boolean singIn(User user) {
        for (User value : users) {
            if (user.getEmail().equals(value.getEmail()) &&
                    user.getPassword().equals(value.getPassword())) {
                customer = value;
                return true;
            }
        }
        return false;

    }


}
