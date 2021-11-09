package com.webApp.controller;

import com.webApp.entity.DebitCard;
import com.webApp.entity.Loan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
public class MainController  {
    List<Loan> loans = new ArrayList<>();
    List <DebitCard> cards = new ArrayList<>();
    @GetMapping("/")
    public String mainPage () {
        return "html/mainPage";
    }
    @GetMapping("/loans")
    public String loans () {
        return "html/loans";
    }
    @GetMapping("/cards")
    public String cards () {
        return "html/cards";
    }
    @GetMapping("/addCard")
    public String addCard (Model model) {
        model.addAttribute("cart", new DebitCard());
        return "html/addCard";
    }
    @PostMapping()
    public String newCart (@ModelAttribute("cart") Model model){
        return "redirect:/html/cards";
    }
    @GetMapping("/addLoan")
    public String addLoan (Model model) {
        model.addAttribute("loan",new Loan());
        return "html/addLoan";
    }

    @PostMapping ()
    public String newLoan(@ModelAttribute("loan") Model model) {
        return "redirect:/html/loans";
    }


}