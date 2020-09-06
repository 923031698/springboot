package com.bjpowernode.springboot;

import com.bjpowernode.springboot.common.utils.Base64;
import com.bjpowernode.springboot.common.utils.RSAUtils2;

import java.net.URLDecoder;
import java.util.Map;

/**
 * @Author: xb
 * @Description: 公钥加密 私钥解密 私钥加密 公钥解密案例
 * @Date: 2020/9/2 15:32
 */
public class RSATester {

    static String publicKey;
    static String privateKey;

    static {
        try {
            Map<String, Object> keyMap = RSAUtils2.genKeyPair();
            publicKey = RSAUtils2.getPublicKey(keyMap);
            privateKey = RSAUtils2.getPrivateKey(keyMap);
            System.err.println("公钥: \n\r" + publicKey);
            System.err.println("私钥： \n\r" + privateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        test();
        //    testSign();
    }

    static void test() throws Exception {
        System.err.println("公钥加密——私钥解密");
        String source = "21312321321321321312398786767867867887687686786787324893278947329847928374983274987328947238947892374893278472374892374123122222222222222222222222222222222222222222222222222223213123123123123123123123123123这是一行没有任何意义的文字，你看完了等于没看，不是吗？";
        System.out.println("\r加密前文字：\r\n" + source);
        // byte[] data = source.getBytes();
        String sign = RSAUtils2.encryptByPublicKey(source.getBytes(), RSAUtils2.PUBLIC_KEY);
        System.out.println("编码:" + sign);

        System.out.println("解码" + URLDecoder.decode(sign, "utf-8"));
        String decode = URLDecoder.decode(sign, "utf-8");

        System.out.println("加密后文字：\r\n" + new String(decode));
        //  byte[] decodedDataStr = ;
        //   System.out.println("加密后文字：\r\n" +new String(decodedDataStr));
        byte[] decodedData = RSAUtils2.decryptByPrivateKey(Base64.decode(decode), RSAUtils2.PRIVATE_KEY);
        // String target = new String(decodedData);
        String target = new String(decodedData, "utf-8");
        System.out.println("解密后文字: \r\n" + target);
    }


    static void testSign() throws Exception {
        System.err.println("私钥加密——公钥解密");
        String source = "这是一行测试RSA数字签名的无意义文字";
        System.out.println("原文字：\r\n" + source);
        byte[] data = source.getBytes();
        byte[] encodedData = RSAUtils2.encryptByPrivateKey(data, privateKey);
        System.out.println("加密后：\r\n" + new String(encodedData));
        byte[] decodedData = RSAUtils2.decryptByPublicKey(encodedData, publicKey);
        String target = new String(decodedData);
        System.out.println("解密后: \r\n" + target);
        System.err.println("私钥签名——公钥验证签名");
        String sign = RSAUtils2.sign(encodedData, privateKey);
        System.err.println("签名:\r" + sign);
        boolean status = RSAUtils2.verify(encodedData, publicKey, sign);
        System.err.println("验证结果:\r" + status);
    }
}