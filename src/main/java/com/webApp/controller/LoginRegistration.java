
package com.webApp.controller;


import com.webApp.entity.ClientEntity;
import com.webApp.services.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
public class LoginRegistration {
    private final ClientService clientService;

    public LoginRegistration(ClientService clientService) {
        this.clientService = clientService;
    }


    @GetMapping("/")
    public String getLoginPage(@ModelAttribute("user") ClientEntity clientEntity) {
        clientService.doLogin(clientEntity);
        return "login";

    }
/*
    @GetMapping("/registration")
    public String getRegisterPage(@ModelAttribute("user") User user) {
        return "registration";
    }

    @PostMapping("/createNewUser")
    public String doRegister(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "redirect:/registration";

         userService.newUser(user);
        return "redirect:/";
    }

    @PostMapping("/login")
    public String doLogin(@ModelAttribute("user") User user) {
        userService.doLogin(user);
        if (userService.getUser().getId() >0) {
            return "redirect:/home";
        }
        return "redirect:/";
    }

    @GetMapping("/restore")
    public String editPass(@ModelAttribute("user") User user) {
        return "restorePassword";
    }

    @PatchMapping("/restorePasswordUser")
    public String editPassword(@ModelAttribute("user") User user) {
        userService.editPassword(user);
        return "redirect:/";
    }

*/
}
