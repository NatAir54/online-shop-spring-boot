package com.nataliiakoval.onlineshopspringboot.service;

import com.nataliiakoval.onlineshopspringboot.entity.Client;
import jakarta.servlet.http.Cookie;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public interface SecurityService {
    void signup(Client client) throws NoSuchAlgorithmException, NoSuchProviderException;

    boolean isClientAuth(Cookie[] cookies);

    String login(Client client);
}
