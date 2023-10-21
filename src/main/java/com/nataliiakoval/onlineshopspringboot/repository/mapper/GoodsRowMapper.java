package com.nataliiakoval.onlineshopspringboot.repository.mapper;

import com.nataliiakoval.onlineshopspringboot.entity.Goods;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Component
public class GoodsRowMapper implements RowMapper<Goods> {

    @Override
    public Goods mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        int price = resultSet.getInt("price");
        LocalDateTime date = resultSet.getTimestamp("date").toLocalDateTime();

        Goods good = Goods.builder().
                id(id).
                name(name).
                price(price).
                date(date).
                build();

        return good;
    }
}
