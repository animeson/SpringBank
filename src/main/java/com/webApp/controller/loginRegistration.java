package com.webApp.controller;
import com.webApp.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class loginRegistration {
//get method
    @GetMapping("/")
    public String login (Model model) {
        return "/login";
    }
    @GetMapping("/registration")
    public String registration (Model model) {
        model.addAttribute("user", new User());
    return "/registration";
    }


//post method
    @PostMapping("/createNewUser")
    public String registrationUser (@ModelAttribute("user") Model model) {
        return "redirect:/registration";
    }

    @PostMapping()
    public String singIn(@ModelAttribute("user") Model model){
        return "redirect:/mainPage";
    }
}
