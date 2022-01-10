package com.webApp.controller;

import com.webApp.dao.DebitCardDao;
import com.webApp.dao.LoanDao;
import com.webApp.entity.DebitCard;
import com.webApp.entity.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    private final DebitCardDao debitCardDao;
    private final LoanDao loanDao;

    @Autowired
    public MainController(DebitCardDao debitCardDao, LoanDao loanDao) {
        this.debitCardDao = debitCardDao;
        this.loanDao = loanDao;
    }


    //get методы
    @GetMapping("/mainPage")
    public String mainPage() {
        return "mainPage";
    }

    //get методы для отображения всех карт и кредитов
    @GetMapping("/loans")
    public String loans(Model model) {


        model.addAttribute("loan", loanDao.showAllLoans());
        return "allLoans";
    }

    @GetMapping("loans/{amount}")
    public String show(@PathVariable("amount") double amount, Model model) {
        model.addAttribute("loan", loanDao.showSelectedCredit(amount));
        return "amountShow";
    }


    @GetMapping("/cards")
    public String cards(Model model) {
        model.addAttribute("card", debitCardDao.showAllCard());
        return "allCards";
    }

    @GetMapping("cards/{card}")
    public String show(@PathVariable("card") String card, Model model) {
        model.addAttribute("cards", debitCardDao.showSelectedCard(card));
        return "cardNumberShow";
    }


    //get методы для отображения страниц создания карты и кредита

    @GetMapping("/create_card")
    public String createNewCards(@ModelAttribute("card") DebitCard debitCard) {
        return "addCard";
    }

    @GetMapping("/create_loan")
    public String createNewLoan(@ModelAttribute("loan") Loan loan) {
        return "addLoan";
    }


    //post методы
    @PostMapping("/addCard")
    public String addCard(@ModelAttribute("card") DebitCard debitCard) {
//        debitCardDao.save(debitCard);
        return "redirect:/cards";
    }

    @PostMapping("/addLoan")
    public String addLoan(@ModelAttribute("loan") Loan loan) {
//        loanDao.save(loan);
        return "redirect:/loans";
    }

}