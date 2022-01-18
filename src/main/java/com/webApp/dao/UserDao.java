package com.webApp.dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;
import com.webApp.entity.User;

@Component
public class UserDao {
   private final JdbcTemplate jdbcTemplate;


    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User showIdUser (String email, String password) {
       return jdbcTemplate.query("select id from client " +
                                "where email =? and password =?",
                        new Object[]{email,password},
                        new BeanPropertyRowMapper<>(User.class)).
                stream().findAny().orElse(null);
    }


    public List<User> showSelectedUser(long ID) {
        return jdbcTemplate.query("select * from client where id=?", new Object[]{ID},
                new BeanPropertyRowMapper<>(User.class));
    }



}