package com.nataliiakoval.onlineshopspringboot.repository.mapper;

import com.nataliiakoval.onlineshopspringboot.entity.Client;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ClientRowMapper implements RowMapper<Client> {
    @Override
    public Client mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        int id = resultSet.getInt("id");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        String sole = resultSet.getString("sole");

        Client client = Client.builder().
                id(id).
                email(email).
                password(password).
                sole(sole).
                build();
        return client;
    }
}
