package com.webApp.dao;

import com.webApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserDao {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }

    public User showSelectedUser(String email, String password) {
        return jdbcTemplate.query("select id, firstname,lastname,email,password,phonenumber,registrationdate from client where email =? and password =?",
                        new BeanPropertyRowMapper<>(User.class), email, password)
                .stream().findAny().orElse(null);
    }

    public void newUser(User user) {
        jdbcTemplate.update("insert into client (firstname, lastname, email, password, phonenumber, registrationdate) values (?,?,?,?,?,?)",
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getPhoneNumber(),
                user.getRegistrationDate());
    }


    public void edit(long id, User updateUser) {
        jdbcTemplate.update("update client set " +
                        "firstname = ?," +
                        "lastname = ?," +
                        "email = ?," +
                        "phonenumber = ?," +
                        "password = ?" +
                        "where id = ?",
                updateUser.getFirstName(),
                updateUser.getLastName(),
                updateUser.getEmail(),
                updateUser.getPhoneNumber(),
                updateUser.getPassword(), id);
    }

    public void editPassword(User user) {
        jdbcTemplate.update("update client set password = ? where email =? and phonenumber =?",
                user.getPassword(),user.getEmail(), user.getPhoneNumber());
    }


}