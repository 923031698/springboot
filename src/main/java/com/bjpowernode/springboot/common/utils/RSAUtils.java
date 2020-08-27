package com.bjpowernode.springboot.common.utils;


import javax.crypto.Cipher;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * Java RSA 加密工具类
 * 参考： https://blog.csdn.net/qy20115549/article/details/83105736
 */
public class RSAUtils {

    /**
     * 测试公钥
     */
    public final static String GET_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAplPsqQvsfXYrpBCJNb2cZEPTULXXEfR194uQDF10smI3Y705wUI5wU9n/MoO5Cj84WBec0opHo4Gr8KQGO6sPzXMD3+/WJRFoUC70W1yNrQkpF3rVfdQBwo9gnAEczVUqTiUIE5MEhRYfzAD88TjZwd2ow7HZgZe+E8v7t/IUeot8dPriZ7p6u7DgC7bZGyVnbrnStkKmg1iFaEqDqL2DaukSFjuZhxJoGy6iOTwXFCeY1PGEi98JtKPBMVDErD0ZdUGoQ0kfw96xYtOQxpaF66gl6S0m1Nfe+wmrbzzGP7Ho7Fz4abc8Ei1GYGE3md4rQvsHry6s0vuL3SqpH8yTwIDAQAB";
    /**
     * 测试私钥
     */
    public final static String GET_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCmU+ypC+x9diukEIk1vZxkQ9NQtdcR9HX3i5AMXXSyYjdjvTnBQjnBT2f8yg7kKPzhYF5zSikejgavwpAY7qw/NcwPf79YlEWhQLvRbXI2tCSkXetV91AHCj2CcARzNVSpOJQgTkwSFFh/MAPzxONnB3ajDsdmBl74Ty/u38hR6i3x0+uJnunq7sOALttkbJWduudK2QqaDWIVoSoOovYNq6RIWO5mHEmgbLqI5PBcUJ5jU8YSL3wm0o8ExUMSsPRl1QahDSR/D3rFi05DGloXrqCXpLSbU1977CatvPMY/sejsXPhptzwSLUZgYTeZ3itC+wevLqzS+4vdKqkfzJPAgMBAAECggEAe5/vTCpvqUTW5QfhZA89mYyMGPY/F7pSoB6cf4RLA3da4gg4UDtwAA2MJejtLoqEguOE8D4T2AEKWULMpatoXUcYGAEEcMssAdHCh8PIW36DqaOhEnFSixkCZNvrgtIFzg0ek/B+PAXhTQ+cMGiL+P0kIcgPTva3SepZnzu3gaHLVAP3iz7JL9L3H61/Hrlum9p06tLyvr2hboz7B3OfcXeg5q7HKkwTo0KZeLPlbZYWvMNWpFegtUvzESSBC2/2n591GF7UhnSOl7J26YJcQ7Du9N/fb8eF5HEjRTyKISg3Slg55zv91YNUyIbDOgMD0TQY2vzIqw3YBQFCxs1GoQKBgQD8ydyfs0RiaLEQLVcWs0RmiJqVkz5M+Zom4nvLWD7TKiOnkYwDanXGRI81htuBEwXkGVibVYD1ORi6OXsyUcyZkfKxDe6n02a00Uf2lmuQb6QKyTa04tAtJIoSnXCh00M8tFj6Sp3vvSClc9Dt+nEl6YNUfWd7NaCBJztHfMbzHwKBgQCocN5Zy3ZoTGiEZ40sStG5YmhnmCVUhh+YMy47oTwBhW/EmCbzUAP3bgQ6RfpUau9P4x7OucTPYDqeycnbW6kpdF1/glVdhmhvcpAG/2nHdD9bfLvqRQoFym+iP7bQDq33JylSSXru9+EFamD2/62pbk5ISaWopL3R2VKevHyK0QKBgQCh2flZgyUwwQlFa1JMZYFRCPu48yq7jEDHztEmVBz9hQiJHKaB9WLtDC6JPgxC61QlRJk385n66JDNg0o+4KLYXNr/xATfDXrAIRMrbgn205Q7l7NgQ6y4iqGg448e/a4aKvqhXq8atYQ2ix3lkSlq9cUxjo14FNDsgJry3ZBgTwKBgH/B/CRHemvpaGzkzeMGbMQbq+zHLalKgbRDPZHk3UqaFwgaD5EE471MKNC6OwDEivcg4UETDf/skQnXK8/BWjEjHbeU94KUa6n8Ttm/6E9sEJInrbAm5U83GC0L/nEYcULA5UqQiTmvsXEWGq29h86255FUqt2a9o6wM0ZK2HvBAoGAYr9XXWRPdkf7W6j1b2btgqKqKe/hD4Q9/LbR/RZ9lWC0Fw84VCl3WZDK9+4+37eutJyl1HSvW5FQRpviizdWXLLPkRsNl0Mgb8DxIysMqtn1X7BsMn5maX40mo2P3aBs++4QftCyklAB0+JIHl1vCUoO4shqocaLkXC3DF3nyGk=";
    /**
     * 密钥长度 于原文长度对应 以及越长速度越慢
     */
    private final static int KEY_SIZE = 2048;

    public final static String PUBLIC_KEY = "publicKey";

    public final static String PRIVATE_KEY = "privateKey";
    /**
     * 用于封装随机产生的公钥与私钥
     */
    private static Map<String, String> keyMap = new HashMap<String, String>();

    /**
     * 随机生成密钥对
     */
    public static Map<String, String> genKeyPair() {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = null;
        try {
            keyPairGen = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 初始化密钥对生成器
        keyPairGen.initialize(KEY_SIZE, new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        // 得到私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        // 得到公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        String publicKeyString = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        // 得到私钥字符串
        String privateKeyString = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        // 将公钥和私钥保存到Map
        //publicKey表示公钥
        keyMap.put(PUBLIC_KEY, publicKeyString);
        //privateKey表示私钥
        keyMap.put(PRIVATE_KEY, privateKeyString);
        return keyMap;
    }

    /**
     * RSA公钥加密
     *
     * @param str       加密字符串
     * @param publicKey 公钥
     * @return 密文
     * @throws Exception 加密过程中的异常信息
     */
    public static String encrypt(String str, String publicKey) throws Exception {
        //base64编码的公钥
        byte[] decoded = Base64.getDecoder().decode(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        String outStr = Base64.getEncoder().encodeToString(cipher.doFinal(str.getBytes("UTF-8")));
        return outStr;
    }

    /**
     * RSA私钥解密
     *
     * @param str        加密字符串
     * @param privateKey 私钥
     * @return 明文
     * @throws Exception 解密过程中的异常信息
     */
    public static String decrypt(String str, String privateKey) throws Exception {
        //64位解码加密后的字符串
        byte[] inputByte = Base64.getDecoder().decode(str);
        //base64编码的私钥
        byte[] decoded = Base64.getDecoder().decode(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        String outStr = new String(cipher.doFinal(inputByte));
        return outStr;
    }


    public static void main(String[] args) throws Exception {
        long temp = System.currentTimeMillis();
        //生成公钥和私钥
        //  genKeyPair();
        //加密字符串
//        System.out.println("公钥:" + keyMap.get(PUBLIC_KEY));
//        System.out.println("私钥:" + keyMap.get(PRIVATE_KEY));
//        System.out.println("生成密钥消耗时间:" + (System.currentTimeMillis() - temp) / 1000.0 + "秒");


        String message = "{\n" +
                "\t\"outTradeNo\": \"12321312312321311231233213\",\n" +
                "\t\"totalAmount\": \"1231231\",\n" +
                "\t\"productCode\": \"334324324\",\n" +
                "\t\"authNo\": \"123123123123123123123\",\n" +
                "\t\"buyerId\": \"123213123123123\",\n" +
                "\t\"sellerId\": \"123213213123213\",\n" +
                "\t\"authConfirmMode\": \"123123\",\n" +
                "\n" +
                "}";
        System.out.println("原文:" + message);

        temp = System.currentTimeMillis();
        String messageEn = encrypt(message, GET_PUBLIC_KEY);
        System.out.println("密文:" + messageEn);
        String encode = URLEncoder.encode(messageEn, "utf-8");
        System.out.println("编码:" + encode);

        System.out.println("加密消耗时间:" + (System.currentTimeMillis() - temp) / 1000.0 + "秒");
        System.out.println("解码" + URLDecoder.decode(encode, "utf-8"));
        temp = System.currentTimeMillis();
        String messageDe = decrypt(URLDecoder.decode(encode, "utf-8"), GET_PRIVATE_KEY);
        System.out.println("解密:" + messageDe);
        System.out.println("解密消耗时间:" + (System.currentTimeMillis() - temp) / 1000.0 + "秒");
    }
}