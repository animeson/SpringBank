package com.webApp.controller;

import com.webApp.dao.DebitCardDao;
import com.webApp.dao.LoanDao;
import com.webApp.dao.UserDao;
import com.webApp.entity.DebitCard;
import com.webApp.entity.Loan;
import com.webApp.entity.User;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("user", userDao.showSelectedUser(LoginRegistration.getUSER_ID()));
        return "mainPage";
    }

    @GetMapping("/id/{ID}")
    public String infoUser(@PathVariable("ID") long id, Model model) {
        if (id == LoginRegistration.getUSER_ID()) {
            model.addAttribute("userInfo", userDao.showSelectedUser(id));
            return "infoUser";
        }
        return "redirect:/home";

    }


    @GetMapping("/edit/{ID}")
    public String editUserInfo(@PathVariable("ID") long ID, Model model) {
        if (ID == LoginRegistration.getUSER_ID()) {
            model.addAttribute("user", userDao.showSelectedUser(ID));
            return "editUserInfo";
        }
        return "redirect:/home";
    }

    //get методы для отображения всех карт и кредитов
    @GetMapping("/loan")
    public String showAllLoan(Model model) {
        model.addAttribute("loans", loanDao.showAllLoans(LoginRegistration.getUSER_ID()));
        return "allLoans";
    }

    @GetMapping("/loan/{amount}")
    public String showAllAmount(@PathVariable("amount") double amount, Model model) {
        model.addAttribute("loan", loanDao.showSelectedCredit(amount));
        return "amountShow";
    }


    @GetMapping("/card")
    public String showAllCard(Model model) {
        model.addAttribute("card", debitCardDao.showAllCardNumberWhereUserId(LoginRegistration.getUSER_ID()));
        return "allCards";
    }

    @GetMapping("/card/{cards}")
    public String show(@PathVariable("cards") String card, Model model) {
        for (int i = 0; i < debitCardDao.getList().size(); i++) {
            if (card.equals(debitCardDao.getList().get(i))) {
                model.addAttribute("cardNumber", debitCardDao.showSelectedCard(card));
                return "cardNumberShow";
            }

        }
        return "redirect:/card";

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
        debitCardDao.saveNewCard(debitCard,
                LoginRegistration.getUSER_ID());
        return "redirect:/card";
    }

    @PostMapping("/addLoan")
    public String addLoan(@ModelAttribute("loan") Loan loan) {
        loanDao.saveNewLoan(loan, LoginRegistration.getUSER_ID());
        return "redirect:/loan";
    }


    //delete методы (удаление карты)
    @DeleteMapping("/card/{cards}")
    public String deleteCard(@PathVariable("cards") String card) {
        debitCardDao.deleteDebitCard(card);
        return "redirect:/card";
    }

    //delete методы (удаление всех карт)
    @NonNull
    @DeleteMapping("/card/delAllCard")
    public String deleteCard() {
        debitCardDao.deleteAllDebitCard(LoginRegistration.getUSER_ID());
        return "redirect:/home";
    }

    //patch методы(редактироваание инвормации о пользователе)
    @PatchMapping("/edit_user{ID}")
    public String updateInfoUser(@ModelAttribute("user") User user, @PathVariable("ID") Long ID) {
        userDao.edit(ID, user);
        return "redirect:/id/{ID}";
    }


}