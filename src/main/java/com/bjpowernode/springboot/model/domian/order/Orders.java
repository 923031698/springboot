package com.bjpowernode.springboot.model.domian.order;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Orders {
    private Integer id;

    private Integer uid;

    private Integer goodsid;

    private Integer buynum;

    private BigDecimal buyprice;

    private Long ordermoney;

    private Date createtime;

    private Integer status;
}