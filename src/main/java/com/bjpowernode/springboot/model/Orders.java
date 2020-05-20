package com.bjpowernode.springboot.model;

import java.math.BigDecimal;
import java.util.Date;

public class Orders {
    private Integer id;

    private Integer uid;

    private Integer goodsid;

    private Integer buynum;

    private BigDecimal buyprice;

    private Long ordermoney;

    private Date createtime;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Integer goodsid) {
        this.goodsid = goodsid;
    }

    public Integer getBuynum() {
        return buynum;
    }

    public void setBuynum(Integer buynum) {
        this.buynum = buynum;
    }

    public BigDecimal getBuyprice() {
        return buyprice;
    }

    public void setBuyprice(BigDecimal buyprice) {
        this.buyprice = buyprice;
    }

    public Long getOrdermoney() {
        return ordermoney;
    }

    public void setOrdermoney(Long ordermoney) {
        this.ordermoney = ordermoney;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}