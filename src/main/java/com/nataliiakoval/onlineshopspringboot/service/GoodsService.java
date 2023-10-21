package com.nataliiakoval.onlineshopspringboot.service;

import com.nataliiakoval.onlineshopspringboot.entity.Goods;

import java.util.List;

public interface GoodsService {
    void addGood(Goods good);

    List<Goods> findAll();

    void remove(int id);

    void update(Goods good, int id);
}
