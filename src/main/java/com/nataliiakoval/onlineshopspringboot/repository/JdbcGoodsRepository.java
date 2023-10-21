package com.nataliiakoval.onlineshopspringboot.repository;

import com.nataliiakoval.onlineshopspringboot.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class JdbcGoodsRepository implements GoodsRepository {
    private static final String SQL_CREATE_TABLE = """
                CREATE TABLE goods (id SERIAL PRIMARY KEY, name varchar(50) NOT NULL, price int NOT NULL, date DATE);""";
    private static final String SQL_INSERT_INTO = "INSERT INTO goods (name, price, date) VALUES (?, ?, NOW());";
    private static final String SQL_FIND_ALL = "SELECT id, name, price, date FROM goods";
    private static final String SQL_UPDATE = "UPDATE goods SET name = ?, price = ?, date = NOW() WHERE id = ?;";
    private static final String SQL_REMOVE = "DELETE FROM goods WHERE id = ?;";

    @Autowired
    private RowMapper<Goods> GOODS_ROW_MAPPER;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public void addGood(Goods good) {
        jdbcTemplate.update(SQL_INSERT_INTO, good.getName(), good.getPrice());
    }

    @Override
    public List<Goods> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL, GOODS_ROW_MAPPER);
    }

    @Override
    public void update(Goods good, int id)  {
        jdbcTemplate.update(SQL_UPDATE, good.getName(), good.getPrice(), id);
    }

    @Override
    public void remove(int id) {
        jdbcTemplate.update(SQL_REMOVE, id);
    }
}
