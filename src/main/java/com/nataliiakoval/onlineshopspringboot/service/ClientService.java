package com.nataliiakoval.onlineshopspringboot.service;

import com.nataliiakoval.onlineshopspringboot.entity.Client;

import java.util.List;

public interface ClientService {
    void add(Client client);

    List<Client> findAll();

    Client findByEmail(String email);
}
