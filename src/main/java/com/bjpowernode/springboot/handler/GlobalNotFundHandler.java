package com.bjpowernode.springboot.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 异常处理类
 */
@Controller
public class GlobalNotFundHandler {

    /**
     * 当发生404的时候，统一走这个地方
     *
     * @return
     */
    @RequestMapping("/404")
    public String notFund() {
        return "40x";
    }
}