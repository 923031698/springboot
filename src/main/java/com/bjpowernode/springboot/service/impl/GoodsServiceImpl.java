package com.bjpowernode.springboot.service.impl;

import com.bjpowernode.springboot.common.utils.ResponseUtils;
import com.bjpowernode.springboot.mapper.goods.GoodsMapper;
import com.bjpowernode.springboot.mapper.orders.OrdersMapper;
import com.bjpowernode.springboot.model.domian.good.Goods;
import com.bjpowernode.springboot.model.domian.order.Orders;
import com.bjpowernode.springboot.common.utils.Response;
import com.bjpowernode.springboot.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {


    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private OrdersMapper ordersMapper;

    public List<Goods> getAllGoods() {
        //return goodsMapper.selectAll();
        return null;
    }

    public Goods getGoodsById(Integer goodsId) {
        return null;
    }

    /**
     * 下单
     *
     * @param uid
     * @param goodsId
     * @param buyNum
     * @return
     */
    @Transactional
    public Response doOrder(Integer uid, Integer goodsId, Integer buyNum) {
        //减库存 (操作商品库)
        Goods goods = new Goods();
        goods.setId(goodsId);
        goods.setStore(buyNum);
        // goodsMapper.updateByPrimaryKeySelective(goods);
        //下订单
        Orders orders = new Orders();
        orders.setBuynum(buyNum);
        orders.setUid(uid);
        orders.setCreatetime(new Date());
        orders.setGoodsid(goodsId);
        //操作订单库
        //    ordersMapper.insertSelective(orders);
        //发生异常，测试是否回滚
        int i = 10 / 0;
        return ResponseUtils.success();
    }


    /*  public Response select() {
     *//* Weekend<Goods> weekend = new Weekend(Goods.class);
        Goods goods = new Goods();
        WeekendCriteria weekendCriteria = weekend.weekendCriteria().andEqualTo(Goods::getName,);


        goodsMapper.selectByExample(weekend);*//*
    }*/
}