package com.nataliiakoval.onlineshopspringboot.repository;


import com.nataliiakoval.onlineshopspringboot.entity.Goods;

import java.util.List;

public interface GoodsRepository {

    void addGood(Goods good);

    List<Goods> findAll();

    void update(Goods good, int id);
    void remove(int id);
}
