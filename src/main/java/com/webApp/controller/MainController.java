
package com.webApp.controller;

import com.webApp.services.ClientService;
import com.webApp.services.DebitCardService;
import com.webApp.services.LoanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.webApp.entity.*;

@Controller
public class MainController {
    private final ClientService clientService;
    private final DebitCardService debitCardService;
    private final LoanService loanService;

    public MainController(ClientService clientService, DebitCardService debitCardService, LoanService loanService) {
        this.clientService = clientService;
        this.debitCardService = debitCardService;
        this.loanService = loanService;
    }

    //user method
    @GetMapping("/home")
    public String mainPage(Model model) {
        model.addAttribute("user", clientService.getUser());
        return "mainPage";
    }

    @GetMapping("/id/{ID}")
    public String infoUser(@PathVariable("ID") long id, Model model) {
        if (id == clientService.getUser().getId()) {
            model.addAttribute("userInfo", clientService.getUser());
            return "infoUser";
        }
        return "redirect:/home";

    }

    @GetMapping("/edit/{ID}")
    public String editUserInfo(@PathVariable("ID") long ID, Model model) {
        if (ID == clientService.getUser().getId()) {
            model.addAttribute("user", clientService.getUser());
            return "editUserInfo";
        }
        return "redirect:/home";
    }


    @PatchMapping("/edit_user{ID}")
    public String updateInfoUser(@ModelAttribute("user") Client user, @PathVariable("ID") Long id) {
        clientService.updateInfoUser(user);
        return "redirect:/id/{ID}";
    }

    // card method
    @GetMapping("/card")
    public String showAllCard(Model model) {
        model.addAttribute("card", clientService.getUser().getDebitCardsById());
        return "allCards";
    }

    @GetMapping("/quantity")
    public String remittance(@ModelAttribute("money") DebitCard debitCard) {
        return "accountReplenishment";
    }

    @GetMapping("/card/{cards}")
    public String show(@PathVariable("cards") String card, Model model) {
        model.addAttribute("cardNum", debitCardService.showSelectedCard(card));
        return "cardNumberShow";
    }

    @PostMapping("/addCard")
    public String addCard(@ModelAttribute("card") DebitCard debitCard) {
        debitCardService.createNewCard(clientService.getUser());
        return "redirect:/card";
    }

    @PostMapping("/accountReplenishment")
    public String sendMoney(@ModelAttribute("card") DebitCard debitCard) {
        debitCardService.remittance(debitCard);
        return "redirect:/card";
    }
    @DeleteMapping("/card/{cards}")
    public String deleteCard(@PathVariable("cards") String cardNumber) {
        debitCardService.deleteDebitCard(cardNumber);
        return "redirect:/card";
    }
    @DeleteMapping("/card/delAllCard")
    public String deleteAllDebitCard() {
        debitCardService.deleteAllDebitCard();
        return "redirect:/home";
    }




    //loan method
    @GetMapping("/loan")
    public String showLoan(Model model) {
        model.addAttribute("loan", clientService.getUser().getLoansById());
        return "allLoans";
    }

    @GetMapping("/loan/{amount}")
    public String showAllAmount(@PathVariable("amount") Double amount, Model model) {
        model.addAttribute("loan", loanService.showAllAmount(amount));
        return "amountShow";
    }

    @PostMapping("/addLoan")
    public String addLoan(@ModelAttribute("loan") Loan loan) {
        loanService.saveNewLoan(loan,clientService.getUser().getId());
        return "redirect:/loan";
    }
    @GetMapping("/create_loan")
    public String createNewLoan(@ModelAttribute("loan") Loan loan) {
        return "addLoan";
    }


}
