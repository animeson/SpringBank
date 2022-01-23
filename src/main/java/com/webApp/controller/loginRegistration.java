package com.webApp.controller;


import com.webApp.dao.UserDao;
import com.webApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class loginRegistration {
    private static long userID;


    private final UserDao userDao;

    @Autowired
    public loginRegistration(UserDao userDao) {
        this.userDao = userDao;
    }

    public static long getUserID() {
        return userID;
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
    @PostMapping("/createNewUser")
    public String doRegister(@ModelAttribute("user") User user) {
        try {
            userDao.newUser(user);
            return "redirect:/";

        } catch (Exception e) {
            return "redirect:/registration";
        }

    }

    @PostMapping("/thisUser")
    public String doLogin(@ModelAttribute("user") User user) {
        try {
            userID = userDao.showIdUser(user.getEmail(), user.getPassword());
            return "redirect:/home";
        } catch (Exception e) {
            return "redirect:/";
        }
    }




}