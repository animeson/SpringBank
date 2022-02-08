package com.webApp.services;

import com.webApp.dao.ClientDao;
import com.webApp.entity.ClientEntity;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@Service
public class ClientService {
    private final ClientDao clientEntity;

    public ClientService(ClientDao clientEntity) {
        this.clientEntity = clientEntity;
    }


    @Transactional
    public ClientEntity doLogin(ClientEntity modelData) {
        return clientEntity.showSelectedUser(modelData.getEmail(), modelData.getPassword());
    }

    public void editPassword(@NonNull ClientEntity modelData) {


    }

    public void newUser(@NonNull ClientEntity newUser) {

    }


    public void updateInfoUser(Long id, ClientEntity modelData) {

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
