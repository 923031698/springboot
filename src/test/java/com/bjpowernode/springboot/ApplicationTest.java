package com.bjpowernode.springboot;

import com.bjpowernode.springboot.common.utils.HttpUtil;
import com.bjpowernode.springboot.common.utils.RSAUtils2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class ApplicationTest {
    private static String url = "https://tapi.lemuji.com/v2";

    /**
     * @description 资金授权冻结接口
     * @date 2020/8/25 11:23
     */
    @Test
    public void freeze() throws Exception {
        Map<String, Object> bizContent = new HashMap<>(20);
        bizContent.put("authCode", "");
        bizContent.put("authCodeType", "");
        bizContent.put("outOrderNo", "");
        bizContent.put("outRequestNo", "");
        bizContent.put("orderTitle", "");
        bizContent.put("amount", "");
        bizContent.put("payeeLogonId", "");
        bizContent.put("payeeUserId", "");
        bizContent.put("payTimeout", "");
        bizContent.put("extraParam", "");
        bizContent.put("productCode", "");
        bizContent.put("enablePayChannels", "");
        bizContent.put("method", "com.lemuji.alipay.ctl.freeze");

        String message = "{\n" +
                "\t\"authCode\": \"123213\",\n" +
                "\t\"authCodeType\": \"123\",\n" +
                "\t\"outOrderNo\": \"334324324\",\n" +
                "\t\"outRequestNo\": \"123123\",\n" +
                "\t\"amount\": 213,\n" +
                "\t\"orderTitle\": 123,\n" +
                "\t\"payeeLogonId\": 123,\n" +
                "\t\"payeeUserId\": 123\n" +
                "\n" +
                "\t\n" +
                "}";
        //公钥加密
        // String encrypt = RSAUtils.encrypt(message, RSAUtils.GET_PUBLIC_KEY);
        //公钥加密 分段加密
        String encrypt = RSAUtils2.encryptByPublicKey(message.getBytes(), RSAUtils2.PUBLIC_KEY);
        bizContent.put("externalSign", encrypt);
        HttpUtil.sendPOST(bizContent, url);
    }

    /**
     * @description 资金授权发码接口
     * @date 2020/8/25 11:23
     */
    @Test
    void voucherCreate() throws Exception {
        Map<String, Object> bizContent = new HashMap<>(20);
        bizContent.put("outOrderNo", "12321321");
        bizContent.put("outRequestNo", "outRequestNo");
        bizContent.put("orderTitle", "orderTitle");
        bizContent.put("amount", "amount");
        bizContent.put("payeeUserId", "payeeUserId");
        bizContent.put("payeeLogonId", "payeeLogonId");
        bizContent.put("payTimeout", "payTimeout");
        bizContent.put("extraParam", "extraParam");
        bizContent.put("productCode", "productCode");
        bizContent.put("transCurrency", "transCurrency");
        bizContent.put("settleCurrency", "settleCurrency");
        bizContent.put("enablePayChannels", "enablePayChannels");
        bizContent.put("identityParams", "identityParams");
        String message = "{\n" +
                "\t\"outOrderNo\": \"123213123123213123213\",\n" +
                "\t\"outRequestNo\": \"12312312312312321312\",\n" +
                "\t\"orderTitle\": \"334324324\",\n" +
                "\t\"amount\": \"123123\",\n" +
                "\t\"payeeUserId\": 213,\n" +
                "\t\"payeeLogonId\": 123,\n" +
                "\t\"productCode\": 123\n" +
                "}";
        bizContent.put("method", "com.lemuji.alipay.ctl.voucher.create");
        //公钥加密
        //  String encrypt = RSAUtils.encrypt(message, RSAUtils.GET_PUBLIC_KEY);
        String encrypt = RSAUtils2.encryptByPublicKey(message.getBytes(), RSAUtils2.PUBLIC_KEY);
        bizContent.put("externalSign", encrypt);
        HttpUtil.sendPOST(bizContent, url);
    }


    /**
     * @description 资金授权撤销接口
     * @date 2020/8/25 11:23
     */

    @Test
    void cancel() throws Exception {
        Map<String, Object> bizContent = new HashMap<>(20);

        bizContent.put("authNo", "authNo");
        bizContent.put("outOrderNo", "outOrderNo");
        bizContent.put("operationId", "operationId");
        bizContent.put("outRequestNo", "outRequestNo");
        bizContent.put("remark", "remark");
        bizContent.put("method", "com.lemuji.alipay.ctl.cancel");
        String message = "{\n" +
                "\t\"authNo\": \"123213123123213123213\",\n" +
                "\t\"outOrderNo\": \"12312312312312321312\",\n" +
                "\t\"operationId\": \"334324324\",\n" +
                "\t\"outRequestNo\": \"123123\",\n" +
                "\t\"remark\": 213\n" +
                "\n" +
                "}";
        //公钥加密
      //  String encrypt = RSAUtils.encrypt(message, RSAUtils.GET_PUBLIC_KEY);
        String encrypt = RSAUtils2.encryptByPublicKey(message.getBytes(), RSAUtils2.PUBLIC_KEY);
        bizContent.put("externalSign", encrypt);
        HttpUtil.sendPOST(bizContent, url);
    }

    /**
     * @description 资金授权解冻接口
     * @date 2020/8/25 11:23
     */
    @Test
    void unfreeze() throws Exception {
        Map<String, Object> bizContent = new HashMap<>(20);
        bizContent.put("authNo", "authNo");
        bizContent.put("outOrderNo", "outOrderNo");
        bizContent.put("operationId", "operationId");
        bizContent.put("outRequestNo", "outRequestNo");
        bizContent.put("remark", "remark");
        String message = "{\n" +
                "\t\"authNo\": \"123213123123213123213\",\n" +
                "\t\"outOrderNo\": \"12312312312312321312\",\n" +
                "\t\"operationId\": \"334324324\",\n" +
                "\t\"outRequestNo\": \"123123\",\n" +
                "\t\"remark\": 213\n" +
                "\n" +
                "}";
        bizContent.put("method", "com.lemuji.alipay.ctl.unfreeze");
        //公钥加密
       // String encrypt = RSAUtils.encrypt(message, RSAUtils.GET_PUBLIC_KEY);
        String encrypt = RSAUtils2.encryptByPublicKey(message.getBytes(), RSAUtils2.PUBLIC_KEY);
        bizContent.put("externalSign", encrypt);
        HttpUtil.sendPOST(bizContent, url);
    }

    /**
     * @description 冻结转支付接口
     * @date 2020/8/25 11:23
     */
    @Test
    void pay() throws Exception {
        Map<String, Object> bizContent = new HashMap<>(20);
        bizContent.put("outTradeNo", "outTradeNo");
        bizContent.put("totalAmount", "totalAmount");
        bizContent.put("productCode", "productCode");
        bizContent.put("authNo", "authNo");
        bizContent.put("subject", "subject");
        bizContent.put("buyerId", "buyerId");
        bizContent.put("sellerId", "sellerId");
        bizContent.put("authConfirmMode", "authConfirmMode");
        String message = "{\n" +
                "\t\"outTradeNo\": \"1232131231232131\",\n" +
                "\t\"totalAmount\": \"1231231\",\n" +
                "\t\"productCode\": \"334324324\",\n" +
                "\t\"authNo\": \"123123\",\n" +
                "\t\"buyerId\": \"123213\",\n" +
                "\t\"sellerId\": \"123213213\",\n" +
                "\t\"authConfirmMode\": \"123123\",\n" +
                "}";
        bizContent.put("method", "com.lemuji.alipay.ctl.pay");
        //公钥加密
       // String encrypt = RSAUtils.encrypt(message, RSAUtils.GET_PUBLIC_KEY);
        String encrypt = RSAUtils2.encryptByPublicKey(message.getBytes(), RSAUtils2.PUBLIC_KEY);
        bizContent.put("externalSign", encrypt);
        HttpUtil.sendPOST(bizContent, url);
    }


    /**
     * @description 交易同步退款接口
     * @date 2020/8/25 11:23
     */
    @Test
    void refund() throws Exception {
        Map<String, Object> bizContent = new HashMap<>(7);
        bizContent.put("outTradeNo", "outTradeNo");
        bizContent.put("tradeNo", "tradeNo");
        bizContent.put("outRequestNo", "outRequestNo");
        bizContent.put("refundAmount", "refundAmount");

        String message = "{\n" +
                "\t\"outTradeNo\": \"123213123123213123213\",\n" +
                "\t\"tradeNo\": \"12312312312312321312\",\n" +
                "\t\"outRequestNo\": \"334324324\",\n" +
                "\t\"refundAmount\": \"123123\"\n" +
                "}";
        bizContent.put("method", "com.lemuji.alipay.ctl.refund");
        //公钥加密
        //String encrypt = RSAUtils.encrypt(message, RSAUtils.GET_PUBLIC_KEY);
        String encrypt = RSAUtils2.encryptByPublicKey(message.getBytes(), RSAUtils2.PUBLIC_KEY);
        bizContent.put("externalSign", encrypt);
        HttpUtil.sendPOST(bizContent, url);
    }


    /**
     * @description 查询对账单下载地址
     * @date 2020/8/25 11:23
     */
    @Test
    void downloadurlQuery() throws Exception {
        Map<String, Object> bizContent = new HashMap<>(7);
        bizContent.put("billType", "billType");
        bizContent.put("billDate", "billDate");
        String message = "{\n" +
                "\t\"billType\": \"123213123123213123213\",\n" +
                "\t\"billDate\": \"12312312312312321312\"\n" +
                "\t\n" +
                "}";
        bizContent.put("method", "com.lemuji.alipay.ctl.downloadurl.query");
        //公钥加密
       // String encrypt = RSAUtils.encrypt(message, RSAUtils.GET_PUBLIC_KEY);
        String encrypt = RSAUtils2.encryptByPublicKey(message.getBytes(), RSAUtils2.PUBLIC_KEY);
        bizContent.put("externalSign", encrypt);
        HttpUtil.sendPOST(bizContent, url);
    }


    /**
     * @description 资金授权操作查询接口
     * @date 2020/8/25 11:23
     */
    @Test
    void detailQuery() throws Exception {
        Map<String, Object> bizContent = new HashMap<>(7);
        bizContent.put("authNo", "authNo");
        bizContent.put("operationId", "operationId");
        bizContent.put("outOrderNo", "outOrderNo");
        bizContent.put("outRequestNo", "outRequestNo");
        String message = "{\n" +
                "\t\"authNo\": \"123213123123213123213\",\n" +
                "\t\"operationId\": \"12312312312312321312\",\n" +
                "\t\"outOrderNo\": \"12312312312312321312\",\n" +
                "\t\"outRequestNo\": \"12312312312312321312\"\n" +
                "\n" +
                "}";
        bizContent.put("method", "com.lemuji.alipay.ctl.detail.query");
        //公钥加密
       // String encrypt = RSAUtils.encrypt(message, RSAUtils.GET_PUBLIC_KEY);
        String encrypt = RSAUtils2.encryptByPublicKey(message.getBytes(),RSAUtils2.PUBLIC_KEY);
        bizContent.put("externalSign", encrypt);
        HttpUtil.sendPOST(bizContent, url);
    }


}