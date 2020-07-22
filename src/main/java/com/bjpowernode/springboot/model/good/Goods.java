package com.bjpowernode.springboot.model.good;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Goods implements Serializable {

    private Integer id;

    private String name;

    private BigDecimal price;

    private Integer store;
}