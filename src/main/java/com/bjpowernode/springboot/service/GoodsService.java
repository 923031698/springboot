package com.bjpowernode.springboot.service;

import com.bjpowernode.springboot.model.good.Goods;
import com.bjpowernode.springboot.common.utils.Response;

import java.util.List;

public interface GoodsService {

    public List<Goods> getAllGoods();

    public Goods getGoodsById(Integer goodsId);

    public Response doOrder(Integer uid, Integer goodsId, Integer buyNum);
}