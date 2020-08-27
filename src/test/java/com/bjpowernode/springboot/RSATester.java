package com.bjpowernode.springboot;

import com.bjpowernode.springboot.common.utils.Base64;
import com.bjpowernode.springboot.common.utils.RSAUtils2;

import java.net.URLDecoder;
import java.util.Map;

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
}