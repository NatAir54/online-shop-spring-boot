package com.nataliiakoval.onlineshopspringboot.service;

import com.nataliiakoval.onlineshopspringboot.entity.Goods;
import com.nataliiakoval.onlineshopspringboot.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsRepository goodsRepository;

    public void addGood(Goods good) {
        goodsRepository.addGood(good);
    }

    public List<Goods> findAll() {
        return goodsRepository.findAll();
    }

    public void remove(int id)  {
        goodsRepository.remove(id);
    }

    public void update(Goods good, int id) {
        goodsRepository.update(good, id);
    }
}
