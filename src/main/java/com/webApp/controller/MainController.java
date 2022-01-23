package com.webApp.controller;

import com.webApp.dao.DebitCardDao;
import com.webApp.dao.LoanDao;
import com.webApp.dao.UserDao;
import com.webApp.entity.DebitCard;
import com.webApp.entity.Loan;
import com.webApp.entity.User;
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
        model.addAttribute("user", userDao.showSelectedUser(loginRegistration.getUserID()));
        return "mainPage";
    }

    @GetMapping("/id/{ID}")
    public String infoUser(@PathVariable("ID") long ID, Model model) {
        if (ID == loginRegistration.getUserID()) {
            model.addAttribute("userInfo", userDao.showSelectedUser(ID));
            return "infoUser";
        }
        return "redirect:/home";

    }


    @GetMapping("/edit/{ID}")
    public String editUserInfo(@PathVariable("ID") long ID, Model model) {
        if (ID == loginRegistration.getUserID()) {
            model.addAttribute("user", userDao.showSelectedUser(ID));
            return "editUserInfo";
        }
        return "redirect:/home";
    }

    @PatchMapping("/edit_user{ID}")
    public String updateInfoUser(@ModelAttribute("user") User user, @PathVariable("ID") Long ID) {
        userDao.edit(ID, user);
        return "redirect:/id/{ID}";
    }


    //get методы для отображения всех карт и кредитов
    @GetMapping("/loan")
    public String loans(Model model) {
        model.addAttribute("loans", loanDao.showAllLoans(loginRegistration.getUserID()));
        return "allLoans";
    }

    @GetMapping("loan{amount}")
    public String show(@PathVariable("amount") double amount, Model model) {
        model.addAttribute("loan", loanDao.showSelectedCredit(amount));
        return "amountShow";
    }


    @GetMapping("/card")
    public String cards(Model model) {
        model.addAttribute("card", debitCardDao.showAllCardNumberWhereUserId(loginRegistration.getUserID()));
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

    @DeleteMapping("/card/{cards}")
    public String deleteCard(@PathVariable("cards") String card) {
        debitCardDao.deleteDebitCard(card);
        return "redirect:/home";
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
                loginRegistration.getUserID());
        return "redirect:/card";
    }

    @PostMapping("/addLoan")
    public String addLoan(@ModelAttribute("loan") Loan loan) {
        loanDao.saveNewLoan(loan, loginRegistration.getUserID());
        return "redirect:/loan";
    }

}