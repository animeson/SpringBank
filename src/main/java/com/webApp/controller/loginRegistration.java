package com.webApp.controller;

import com.webApp.dao.UserDao;
import com.webApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.SimpleDateFormat;


@Controller
public class loginRegistration {
    private final UserDao userDao;

    @Autowired
    public loginRegistration(UserDao userDao) {
        this.userDao = userDao;
    }


    //get методы
    @GetMapping("/")
    public String getLoginPage(@ModelAttribute("user") User user) {
        return "login";

   }

    @GetMapping("/registration")
    public String getRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    //post метооды
    @PostMapping("/createNewUser")
    public String doRegister(@ModelAttribute("user") User user) {
        //user.setRegistrationDate();
        /*userDao.save(user);*/
        return "redirect:/";
    }

    @PostMapping("/thisUser")
    public String doLogin(@ModelAttribute("user") User user) {
        userDao.showAllUsers();
        if (userDao.singIn(user)) {
            return "redirect:/home";
        }
        return "redirect:/";
    }

    @GetMapping("/id{ID}")
    public String infoUser(@PathVariable("ID") long id, Model model) {
        if (userDao.getCustomer().getId() == id) {
            model.addAttribute("user", userDao.showSelectedUser(id));
            return "infoUser";
        }
        return "redirect:/home";



    }

}