package com.webApp.controller;


import com.webApp.dao.UserDao;
import com.webApp.entity.User;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
public class LoginRegistration {
    @Getter
    private static long USER_ID;

    private final UserDao userDao;

    @Autowired
    public LoginRegistration(UserDao userDao) {
        this.userDao = userDao;
    }

    //get методы
    @GetMapping("/")
    public String getLoginPage(@ModelAttribute("user") User user) {
        return "login";

    }

    @GetMapping("/registration")
    public String getRegisterPage(@ModelAttribute("user") User user) {
        return "registration";
    }


    //post метооды
    @NonNull
    @PostMapping("/createNewUser")
    public String doRegister(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "redirect:/registration";
            userDao.newUser(user);
            return "redirect:/";
    }

    @PostMapping("/thisUser")
    public String doLogin(@ModelAttribute("user") User user) {
        try {
            USER_ID = userDao.showIdUser(user.getEmail(), user.getPassword());
            return "redirect:/home";
        } catch (Exception e) {
            return "redirect:/";
        }
    }


    @GetMapping("/restore")
    public String editPass(@ModelAttribute("user") User user) {
        return "restorePassword";
    }


    // patch методы когда забыл пароль и не знаешь что делать и весь такой в панике аааааа
    // клац на кнопку и всё
    @PatchMapping("/restorePasswordUser")
    public String editPassword(@ModelAttribute("user") User user) {
        userDao.editPassword(user.getPassword(),user.getEmail(), user.getPhoneNumber());
        return "redirect:/";
    }


}