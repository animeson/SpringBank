
package com.webApp.controller;

import com.webApp.services.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {
    private final ClientService clientService;

    public MainController(ClientService clientService) {
        this.clientService = clientService;
    }

    //user method
    @GetMapping("/home")
    public String mainPage(Model model) {
/*        model.addAttribute("user", clientService.doLogin());*/
        return "mainPage";
    }

//    @GetMapping("/id/{ID}")
//    public String infoUser(@PathVariable("ID") long id, Model model) {
//
//        return "redirect:/home";
//
//    }
//
//    @GetMapping("/edit/{ID}")
//    public String editUserInfo(@PathVariable("ID") long ID, Model model) {
//
//        return "editUserInfo";
//    }
//
//    @PatchMapping("/edit_user{ID}")
//    public String updateInfoUser(@ModelAttribute("user") User user, @PathVariable("ID") Long id) {
//        userService.updateInfoUser(id, user);
//        return "redirect:/id/{ID}";
//    }
//
//    //loan method
//    @GetMapping("/loan")
//    public String showLoan(Model model) {
//
//        return "allLoans";
//    }
//
//    @GetMapping("/loan/{amount}")
//    public String showAllAmount(@PathVariable("amount") Double amount, Model model) {
//
//        model.addAttribute("loan", loanService.showAllAmount(amount));*//*
//
//        return "amountShow";
//    }
//
//    @PostMapping("/addLoan")
//    public String addLoan(@ModelAttribute("loan") Loan loan) {
//        loanService.saveNewLoan(loan, userService.getUser().getId());*//*
//
//        return "redirect:/loan";
//    }
//
//    @PostMapping("/repayment")
//    public String repayment() {
//        loanService.loanRepayment();
//        return "redirect:/loan";
//    }
//
//    @GetMapping("/create_loan")
//    public String createNewLoan(@ModelAttribute("loan") Loan loan) {
//        return "addLoan";
//    }
//
//    // card method
//    @GetMapping("/card")
//    public String showAllCard(Model model) {
//        model.addAttribute("card", );
//        return "allCards";
//    }
//
//    @GetMapping("/quantity")
//    public String remittance(@ModelAttribute("money") DebitCard debitCard) {
//        return "accountReplenishment";
//    }
//
//    @PostMapping("/accountReplenishment")
//    public String sendMoney(@ModelAttribute("card") DebitCard debitCard) {
//
//        return "redirect:/card";
//    }
//
//    @GetMapping("/card/{cards}")
//    public String show(@PathVariable("cards") String card, Model model) {
//
//        return "cardNumberShow";
//    }
//
//    @PostMapping("/addCard")
//    public String addCard(@ModelAttribute("card") DebitCard debitCard) {
//
//        return "redirect:/card";
//    }
//
//
//    @DeleteMapping("/card/{cards}")
//    public String deleteCard(@PathVariable("cards") String cardNumber) {
//
//        return "redirect:/card";
//    }
//
//    @NonNull
//    @DeleteMapping("/card/delAllCard")
//    public String deleteAllDebitCard() {
//
//        return "redirect:/home";
//    }

}
