package com.webApp.dao;

import com.webApp.entity.User;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@Component
public class UserDao extends ConnectionToDb {
    private ResultSet resultSet;
    private User customer;
    private PreparedStatement preparedStatement;
    private User user = null;

    public User showAllUsers(User client) {
        try {
            preparedStatement = connection.prepareStatement("select id from client where email =? and password =?");
            preparedStatement.setString(1, client.getEmail());
            preparedStatement.setString(2, client.getPassword());
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user = new User();
            user.setId(resultSet.getLong("id"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean singIn() {
        if (user.getId() > 0) {
            setCustomer(user);
            return true;
        }
        return false;

    }
    /*
        public void save (User user){
            users.add(user);
        }*/


    public User showSelectedUser(long id) {
        User user = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM client where id=?");
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setPhoneNumber(resultSet.getString("phoneNumber"));
                user.setRegistrationDate(resultSet.getDate("registrationDate").toLocalDate());
/*                user.setCard(debitCardDao.showAllCard());
                user.setLoan(loanDao.showAllLoans());*/

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public User getCustomer() {
        return customer;
    }


}
