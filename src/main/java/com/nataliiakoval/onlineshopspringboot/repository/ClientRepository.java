package com.nataliiakoval.onlineshopspringboot.repository;

import com.nataliiakoval.onlineshopspringboot.entity.Client;

import java.util.List;

public interface ClientRepository {
    List<Client> findAll();

    void add(Client client);

    Client findByEmail(String email);
}
