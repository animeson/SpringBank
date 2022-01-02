package com.webApp.controller;

import com.webApp.entity.DebitCard;
import com.webApp.entity.Loan;
import com.webApp.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController  {
   //get method
    @GetMapping("/mainPage")
    public String mainPage () {
        return "/mainPage";
    }
    @GetMapping("/loans")
    public String loans () {
        return "/loans";
    }
    @GetMapping("/cards")
    public String cards () {
        return "/cards";
    }
    @GetMapping("/addCard")
    public String addCard (Model model) {
        model.addAttribute("card", new DebitCard());
        return "/addCard";
    }

    @GetMapping("/addLoan")
    public String addLoan (Model model) {
        model.addAttribute("loan", new Loan());
        return "/addLoan";
    }
//post method
    @PostMapping ("/createNewLoan")
    public String newLoan(@ModelAttribute("loan") Model model) {
        return "redirect:/loans";
    }
    @PostMapping("/createNewCard")
    public String newCart (@ModelAttribute("cart") Model model){
        return "redirect:/cards";
    }

}