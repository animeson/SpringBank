package com.webApp.services;

import com.webApp.dao.ClientDao;
import com.webApp.dao.CodeDao;
import com.webApp.entity.Client;
import com.webApp.entity.Code;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDate;


@Service
@Transactional
public class ClientService {

    private final ClientDao clientDao;
    private final CodeDao codeDao;
    private final EmailService emailService;
    private Client authorizedUser;

    public Client getUser() {
        return clientDao.showSelectedUser(authorizedUser.getEmail(), authorizedUser.getPassword());
    }

    public ClientService(ClientDao clientDao, EmailService emailService, CodeDao codeDao) {
        this.clientDao = clientDao;
        this.emailService = emailService;
        this.codeDao = codeDao;
    }

    public void doLogin(Client modelData) {
        authorizedUser = clientDao.showSelectedUser(modelData.getEmail(),
                getSHA512SecurePassword(modelData.getPassword(), modelData.getEmail()));
    }

    public void searchUser(String clientEmail) {
        Client client = clientDao.searchUser(clientEmail);
        send(clientEmail, client, secureCode());
    }

    public void newUser(Client newUser) {
        newUser.setDateOfRegistration(LocalDate.now());
        newUser.setPassword(getSHA512SecurePassword(newUser.getPassword(), newUser.getEmail()));
        clientDao.newUser(newUser);
    }


    public void editPassword(Client client) {
        Client user = clientDao.searchUserById(client.getId());
        user.setPassword(getSHA512SecurePassword(client.getPassword(), user.getEmail()));
        codeDao.del(user.getId());
        clientDao.editPassword(user);


    }


    public void updateInfoUser(Client modelData) {
        authorizedUser.setFirstName(modelData.getFirstName());
        authorizedUser.setLastName(modelData.getLastName());
        authorizedUser.setPassword(modelData.getPassword());
        authorizedUser.setEmail(modelData.getEmail());
        authorizedUser.setPhoneNumber(modelData.getPhoneNumber());
        clientDao.edit(authorizedUser);


    }


    public void send(String to, Client client, String secureCode) {
        Code code = new Code();
        code.setCode(secureCode);
        code.setExpirationDate(LocalDate.now().plusDays(1));
        code.setUserId(client.getId());
        codeDao.send(code);
        emailService.sendMail(to, client.getFirstName(), client.getLastName(), secureCode);


    }

    public String secureCode() {
        SecureRandom random = new SecureRandom();
        return random.ints(48, 90)
                .filter(i -> (i < 57 || i > 65))
                .mapToObj(i -> (char) i)
                .limit(25)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }


    public String getSHA512SecurePassword(String passwordToHash, String salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
}
