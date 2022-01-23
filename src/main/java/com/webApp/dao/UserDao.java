package com.webApp.dao;

import com.webApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;

@Component
public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public Long showIdUser(String email, String password) {
        return jdbcTemplate.queryForObject("select id from client where email =? and password =?",
                Long.class, email, get_SHA_512_SecurePassword(password,email));

    }


    public User showSelectedUser(Long id) {
        return jdbcTemplate.query("select * from client where id=?",
                        new BeanPropertyRowMapper<>(User.class), id)
                .stream().findAny().orElse(null);
    }

    public void newUser(User user) {
        LocalDate date = LocalDate.now();
        jdbcTemplate.update("insert into client values (?,?,?,?,?,?,?)",
                1,
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                get_SHA_512_SecurePassword(user.getPassword(),user.getEmail()),
                user.getPhoneNumber(),
                date);
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
                get_SHA_512_SecurePassword(updateUser.getPassword(),updateUser.getEmail()), id);
    }


    public String get_SHA_512_SecurePassword(String passwordToHash, String salt){
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return generatedPassword;
    }




}