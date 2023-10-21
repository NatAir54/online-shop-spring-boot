package com.nataliiakoval.onlineshopspringboot.repository;

import com.nataliiakoval.onlineshopspringboot.entity.Client;
import com.nataliiakoval.onlineshopspringboot.repository.ClientRepository;
import com.nataliiakoval.onlineshopspringboot.repository.mapper.ClientRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;


@Repository
public class JdbcClientRepository implements ClientRepository {
    private static final String SQL_CREATE_TABLE = """
                CREATE TABLE clients (id SERIAL PRIMARY KEY, email varchar(50) NOT NULL, password varchar(50) NOT NULL, sole varchar(150) NOT NULL);""";
    private static final String SQL_INSERT_INTO = "INSERT INTO clients (email, password, sole) VALUES (?, ?, ?);";
    private static final String SQL_FIND_ALL = "SELECT id, email, password, sole FROM clients;";

    @Autowired
    private RowMapper<Client> clientRowMapper;
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void add(Client client) {
        jdbcTemplate.update(SQL_INSERT_INTO, client.getEmail(), client.getPassword(), client.getSole());
    }

    @Override
    public Client findByEmail(String email) {
        List<Client> list = findAll();
        for (Client dbClient : list) {
            if (Objects.equals(dbClient.getEmail(), email)) {
                return dbClient;
            }
        }
        return null;
    }

    public List<Client> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL, clientRowMapper);
    }
}
