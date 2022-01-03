package com.webApp.controller;

import com.webApp.entity.DebitCard;
import com.webApp.entity.Loan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController  {
   //get методы
    @GetMapping("/mainPage")
    public String mainPage () {
        return "mainPage";
    }
    @GetMapping("/loans")
    public String loans (Model model) {
        return "allLoans";
    }
    @GetMapping("/cards")
    public String cards (Model model) {
        return "allCards";
    }

    //get методы для отображения страниц создания карты и кредита

    @GetMapping("/create_card")
    public String createNewCards (@ModelAttribute("card") DebitCard debitCard) {
        return "addCard";
    }


    @GetMapping("/create_loan")
    public String createNewLoan (@ModelAttribute("loan") Loan loan) {
        return "addLoan";
    }



//post методы
    @PostMapping("/addCard")
    public String addCard (@ModelAttribute("card") DebitCard debitCard) {
        return "redirect:/cards";
    }

    @PostMapping("/addLoan")
    public String addLoan (@ModelAttribute("loan") Loan loan) {
        return "redirect:/loans";
    }

}