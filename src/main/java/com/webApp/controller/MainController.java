package com.webApp.controller;

import com.webApp.dao.DebitCardDao;
import com.webApp.dao.LoanDao;
import com.webApp.dao.UserDao;
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
    private final UserDao userDao;

    @Autowired
    public MainController(DebitCardDao debitCardDao, LoanDao loanDao, UserDao userDao) {
        this.debitCardDao = debitCardDao;
        this.loanDao = loanDao;
        this.userDao = userDao;
    }


    //get методы
    @GetMapping("/home")
    public String mainPage(Model model) {
        model.addAttribute("user", userDao.showSelectedUser(userDao.getCustomer().getId()));
        return "mainPage";
    }

    //get методы для отображения всех карт и кредитов
    @GetMapping("/loan")
    public String loans(Model model) {
        model.addAttribute("loans", loanDao.showAllLoans());
        return "allLoans";
    }

    @GetMapping("loan{amount}")
    public String show(@PathVariable("amount") double amount, Model model) {
        model.addAttribute("loan", loanDao.showSelectedCredit(amount));
        return "amountShow";
    }


    @GetMapping("/card")
    public String cards(Model model) {
        model.addAttribute("card", debitCardDao.showAllCard());
        return "allCards";
    }

    @GetMapping("card{cards}")
    public String show(@PathVariable("cards") String card, Model model) {
        model.addAttribute("card", debitCardDao.showSelectedCard(card));
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
        /*debitCardDao.save(debitCard);*/
        return "redirect:/card";
    }

    @PostMapping("/addLoan")
    public String addLoan(@ModelAttribute("loan") Loan loan) {
//        loanDao.save(loan);
        return "redirect:/loan";
    }

}