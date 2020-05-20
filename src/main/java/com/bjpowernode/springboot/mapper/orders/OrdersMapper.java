package com.bjpowernode.springboot.mapper.orders;

import com.bjpowernode.springboot.model.order.Orders;

public interface OrdersMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);
}