package com.bjpowernode.springboot.service;

import com.bjpowernode.springboot.model.Goods;
import com.bjpowernode.springboot.model.ResultObject;

import java.util.List;

public interface GoodsService {

    public List<Goods> getAllGoods();

    public Goods getGoodsById(Integer goodsId);

    public ResultObject doOrder(Integer uid, Integer goodsId, Integer buyNum);
}