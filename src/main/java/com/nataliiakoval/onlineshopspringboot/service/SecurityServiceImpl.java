package com.nataliiakoval.onlineshopspringboot.service;

import com.nataliiakoval.onlineshopspringboot.entity.Client;
import com.nataliiakoval.onlineshopspringboot.repository.ClientRepository;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.digest.DigestUtils;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.*;

@Service
public class SecurityServiceImpl implements SecurityService {
    @Autowired
    private ClientRepository clientRepository;
    private final List<String> CLIENT_TOKENS = Collections.synchronizedList(new ArrayList<>());

    @Override
    public void signup(Client client) throws NoSuchAlgorithmException, NoSuchProviderException {
        String sole = generateSole();
        client.setSole(sole);
        String hashPassword = DigestUtils.md5Hex(client.getSole() + client.getPassword());
        client.setPassword(hashPassword);
        clientRepository.add(client);
    }

    @Override
    public boolean isClientAuth(Cookie[] cookies) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user-token")) {
                    if (CLIENT_TOKENS.contains(cookie.getValue())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public String login(Client client) {
        if (isPasswordCorrect(client)) {
            return generateToken();
        } else {
            return null;
        }
    }

    private String generateToken() {
        String userToken = UUID.randomUUID().toString();
        CLIENT_TOKENS.add(userToken);
        return userToken;
    }

    private boolean isPasswordCorrect(Client loginClient) {
        Client dbClient = clientRepository.findByEmail(loginClient.getEmail());
        if (dbClient != null) {
            String hashPassword = DigestUtils.md5Hex(dbClient.getSole() + loginClient.getPassword());
            if (dbClient.getPassword().equals(hashPassword)) {
                return true;
            }
        }
        return false;
    }

    private String generateSole() throws NoSuchAlgorithmException, NoSuchProviderException {
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG", "SUN");
        byte[] soleBytes = new byte[16];
        secureRandom.nextBytes(soleBytes);
        return Arrays.toString(soleBytes);
    }
}
