package com.bjpowernode.springboot.controller;

import com.bjpowernode.springboot.constants.Constant;
import com.bjpowernode.springboot.model.Goods;
import com.bjpowernode.springboot.model.ResultObject;
import com.bjpowernode.springboot.model.Users;
import com.bjpowernode.springboot.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 查询所有商品
     *
     * @param model
     * @return
     */
    @RequestMapping("/")
    public String goods(Model model) {
        List<Goods> goodsList = goodsService.getAllGoods();

        model.addAttribute("goodsList", goodsList);

        return "goods";
    }

    @RequestMapping("/boot/toOrder")
    public String toOrder(Model model, @RequestParam("goodsId") Integer goodsId) {
        Goods goods = goodsService.getGoodsById(goodsId);
        model.addAttribute("goods", goods);

        return "order";
    }

    /**
     * 下单
     *
     * @param request
     * @param goodsId
     * @param buyNum
     * @return
     */
    @RequestMapping("/boot/order")
    public @ResponseBody String order(HttpServletRequest request,
                                      @RequestParam("goodsId") Integer goodsId,
                                      @RequestParam("buyNum") Integer buyNum) {

        Users users = (Users)request.getSession().getAttribute(Constant.LOGIN_USER);

        ResultObject resultObject = goodsService.doOrder(users.getId(), goodsId, buyNum);

        return "<script>window.parent.orderOK('"+resultObject.getStatusCode()+"')</script>";
    }
}