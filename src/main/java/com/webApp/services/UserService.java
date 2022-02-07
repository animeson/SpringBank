package com.webApp.services;

import com.webApp.dao.UserDao;
import com.webApp.entity.User;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Objects;


@Service
public class UserService {
    private final UserDao userDao;

    @Getter
    @Setter
    private User user;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }


    public void doLogin(User modelData) {
        user = new User();
        if (userDao.showSelectedUser(modelData.getEmail(), getSHA512SecurePassword(modelData.getPassword(), modelData.getEmail())) != null) {
            user = userDao.showSelectedUser(modelData.getEmail(), getSHA512SecurePassword(modelData.getPassword(), modelData.getEmail()));

        }

    }

    public void editPassword(@NonNull User modelData) {
        if (!Objects.equals(modelData.getPassword(), "")) {
            modelData.setPassword(getSHA512SecurePassword(modelData.getPassword(), modelData.getEmail()));
            userDao.editPassword(modelData);
        }

    }

    public void newUser(@NonNull User newUser) {
        newUser.setRegistrationDate(LocalDate.now());
        newUser.setPassword(getSHA512SecurePassword(newUser.getPassword(), newUser.getEmail()));
        userDao.newUser(newUser);
    }


    public void updateInfoUser(Long id, User modelData) {
        modelData.setPassword(getSHA512SecurePassword(modelData.getPassword(), modelData.getEmail()));
        userDao.edit(id, modelData);

    }


    public String getSHA512SecurePassword(String passwordToHash, String salt) {
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
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
}
