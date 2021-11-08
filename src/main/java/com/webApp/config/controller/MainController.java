package com.webApp.config.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController  {
   /* List<Loan> loans = new ArrayList<>();
    List <DebitCard> cards = new ArrayList<>();*/


    @GetMapping("/first")
    public String mainPage () {
        return "first/home";
    }


}