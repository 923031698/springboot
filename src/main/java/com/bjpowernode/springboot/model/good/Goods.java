package com.bjpowernode.springboot.model.good;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Goods {

    private Integer id;

    private String name;

    private BigDecimal price;

    private Integer store;
}