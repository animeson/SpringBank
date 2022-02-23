
package com.webApp.controller;

import com.webApp.dao.CodeDao;
import com.webApp.entity.Client;
import com.webApp.entity.Code;
import com.webApp.services.ClientService;
import lombok.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@Controller
public class LoginRegistration {
    private final ClientService clientService;
    private final CodeDao codeDao;
    private long id;

    public LoginRegistration(ClientService clientService, CodeDao codeDao) {
        this.clientService = clientService;
        this.codeDao = codeDao;
    }


    @GetMapping("/")
    public String getLoginPage(@ModelAttribute("user") Client client) {
        return "login";

    }

    @PostMapping("/login")
    public String doLogin(@ModelAttribute("user") Client client) {
        clientService.doLogin(client);
        return "redirect:/home";

    }

    @GetMapping("/registration")
    public String getRegisterPage(@ModelAttribute("user") Client user) {
        return "registration";
    }

    @PostMapping("/createNewUser")
    public String doRegister(@ModelAttribute("user") Client client) {
        clientService.newUser(client);
        return "redirect:/";
    }

    @GetMapping("/restore")
    public String editPass(@ModelAttribute("user") Client user) {
        return "searchUser";
    }

    @PostMapping("/searchUser")
    public String searchUser(@ModelAttribute("email") String clientEmail) {
        clientService.searchUser(clientEmail);
        return "emailMessage";
    }

    @GetMapping("restore/{userCode}")
    public String userCode(@PathVariable @NonNull String userCode) {
        Code code = codeDao.search(userCode);
        if (code.getCode().equals(userCode) &&
                code.getExpirationDate().equals(LocalDate.now().plusDays(1))) {
            id = code.getUserId();
            return "redirect:/restoreUserPassword";
        }
        return "redirect:/";

    }

    @GetMapping ("/restoreUserPassword")
    public String editPassword (@ModelAttribute("client") Client client) {
        return "inputNewPassword";
    }


    @PatchMapping("/saveNewPassword")
    public String newPassword (@ModelAttribute ("client") Client client) {
        client.setId(id);
        clientService.editPassword(client);
        return "redirect:/";
    }

}
