package com.webApp.controller;

import com.webApp.entity.DebitCard;
import com.webApp.entity.Loan;
import com.webApp.entity.User;
import com.webApp.services.DebitCardService;
import com.webApp.services.LoanService;
import com.webApp.services.UserService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class MainController {
    private final UserService userService;
    private String password;
    private final LoanService loanService;
    private final DebitCardService debitCardService;

    @Autowired
    public MainController(UserService userService, LoanService loanService, DebitCardService debitCardService) {
        this.userService = userService;

        this.loanService = loanService;
        this.debitCardService = debitCardService;
    }


    //user method
    @GetMapping("/home")
    public String mainPage(Model model) {
        model.addAttribute("user", userService.getUser());
        return "mainPage";
    }

    @GetMapping("/id/{ID}")
    public String infoUser(@PathVariable("ID") long id, Model model) {
        if (id == userService.getUser().getId()) {
            model.addAttribute("userInfo", userService.getUser());
            return "infoUser";
        }
        return "redirect:/home";

    }

    @GetMapping("/edit/{ID}")
    public String editUserInfo(@PathVariable("ID") long ID, Model model) {
        if (ID == userService.getUser().getId()) {
            model.addAttribute("user", userService.getUser());
            return "editUserInfo";
        }
        return "redirect:/home";
    }

    @PatchMapping("/edit_user{ID}")
    public String updateInfoUser(@ModelAttribute("user") User user, @PathVariable("ID") Long id) {
        userService.updateInfoUser(id, user);
        return "redirect:/id/{ID}";
    }

    //loan method
    @GetMapping("/loan")
    public String showLoan(Model model) {
        model.addAttribute("loan", loanService.showLoan(userService.getUser().getId()));
        return "allLoans";
    }

    @GetMapping("/loan/{amount}")
    public String showAllAmount(@PathVariable("amount") Double amount, Model model) {
        model.addAttribute("loan", loanService.showAllAmount(amount));
        return "amountShow";
    }

    @PostMapping("/addLoan")
    public String addLoan(@ModelAttribute("loan") Loan loan) {
        loanService.saveNewLoan(loan, userService.getUser().getId());
        return "redirect:/loan";
    }

    @PostMapping("/repayment")
    public String repayment () {
        loanService.loanRepayment();
        return "redirect:/loan";
    }

    @GetMapping("/create_loan")
    public String createNewLoan(@ModelAttribute("loan") Loan loan) {
        return "addLoan";
    }

    // card method
    @GetMapping("/card")
    public String showAllCard(Model model) {
        model.addAttribute("card", debitCardService.showCard(userService.getUser().getId()));
        return "allCards";
    }

    @GetMapping("/quantity")
    public String remittance (@ModelAttribute("money") DebitCard debitCard){
        return "accountReplenishment";
    }

    @PostMapping("/accountReplenishment")
    public String sendMoney(@ModelAttribute("card") DebitCard debitCard) {
        debitCardService.remittance(debitCard);
        return "redirect:/card";
    }

    @GetMapping("/card/{cards}")
    public String show(@PathVariable("cards") String card, Model model) {
        model.addAttribute("cardNum", debitCardService.showSelectedCard(card));
        return "cardNumberShow";
    }

    @PostMapping("/addCard")
    public String addCard(@ModelAttribute("card") DebitCard debitCard) {
        debitCardService.createNewCard(userService.getUser().getFirstName(),
                userService.getUser().getLastName(), userService.getUser().getId());
        return "redirect:/card";
    }


    @DeleteMapping("/card/{cards}")
    public String deleteCard(@PathVariable("cards") String cardNumber) {
        debitCardService.deleteDebitCard(cardNumber);
        return "redirect:/card";
    }

    @NonNull
    @DeleteMapping("/card/delAllCard")
    public String deleteAllDebitCard() {
        debitCardService.deleteAllDebitCard(userService.getUser().getId());
        return "redirect:/home";
    }

}