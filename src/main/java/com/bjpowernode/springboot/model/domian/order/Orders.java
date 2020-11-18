package com.bjpowernode.springboot.model.domian.order;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xb
 */
@Data
public class Orders implements Serializable {
    private static final long serialVersionUID = -1604147638451589283L;
    private Integer id;

    private Integer uid;

    private Integer goodsid;

    private Integer buynum;

    private BigDecimal buyprice;

    private Long ordermoney;

    private Date createtime;

    private Integer status;
}