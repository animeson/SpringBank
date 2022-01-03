package com.webApp.controller;
import com.webApp.dao.UserDao;
import com.webApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class loginRegistration {

    private final UserDao userDao;
    @Autowired
    public loginRegistration(UserDao userDao) {
        this.userDao = userDao;
    }


    //get method
    @GetMapping("/")
    public String login (@ModelAttribute("user") User user) {
        return "login";
    }

    @GetMapping("/thisUser")
    public String singIn (@ModelAttribute("user") User user) {
        return "redirect:/mainPage";

    }



    @GetMapping("/registration")
    public String registration (Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

//post method
    @PostMapping("/createNewUser")
    public String registrationUser (@ModelAttribute("user") Model model) {
        return "redirect:/";
    }


}
