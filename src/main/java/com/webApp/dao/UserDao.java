package com.webApp.dao;

import com.webApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDao extends ConnectionToDb {
    private User customer;
    private List<User> users;
/*    private final LoanDao loanDao;
    private final DebitCardDao debitCardDao;

    @Autowired
    public UserDao(LoanDao loanDao, DebitCardDao debitCardDao) {
        this.loanDao = loanDao;
        this.debitCardDao = debitCardDao;
    }*/


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
            ResultSet resultSet = statement.executeQuery("SELECT * FROM client");

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setPhoneNumber(resultSet.getString("phoneNumber"));
                user.setRegistrationDate(resultSet.getDate("registrationDate").toLocalDate());
/*                user.setCard(debitCardDao.showAllCard());
                user.setLoan(loanDao.showAllLoans());*/
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

    public User showSelectedUser(long id) {
        return users.stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }


    public boolean singIn(User user) {
        for (User currentUser : users) {
            if (user.getEmail().equals(currentUser.getEmail()) && user.getPassword().equals(currentUser.getPassword())) {
                customer = currentUser;
                return true;
            }
        }
        return false;

    }


}
