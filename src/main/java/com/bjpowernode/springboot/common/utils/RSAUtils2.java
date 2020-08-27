package com.bjpowernode.springboot.common.utils;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.net.URLEncoder;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class RSAUtils2 {

    /** *//**
     * 加密算法RSA
     */
    public static final String KEY_ALGORITHM = "RSA";

    /** *//**
     * 签名算法
     */
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    /** *//**
     * 获取公钥的key
     */
//    private static final String PUBLIC_KEY = "RSAPublicKey";
    /** *//**
     * 获取私钥的key
     */
//    private static final String PRIVATE_KEY = "RSAPrivateKey";


    public final static String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAplPsqQvsfXYrpBCJNb2cZEPTULXXEfR194uQDF10smI3Y705wUI5wU9n/MoO5Cj84WBec0opHo4Gr8KQGO6sPzXMD3+/WJRFoUC70W1yNrQkpF3rVfdQBwo9gnAEczVUqTiUIE5MEhRYfzAD88TjZwd2ow7HZgZe+E8v7t/IUeot8dPriZ7p6u7DgC7bZGyVnbrnStkKmg1iFaEqDqL2DaukSFjuZhxJoGy6iOTwXFCeY1PGEi98JtKPBMVDErD0ZdUGoQ0kfw96xYtOQxpaF66gl6S0m1Nfe+wmrbzzGP7Ho7Fz4abc8Ei1GYGE3md4rQvsHry6s0vuL3SqpH8yTwIDAQAB";

    public final static String PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCmU+ypC+x9diukEIk1vZxkQ9NQtdcR9HX3i5AMXXSyYjdjvTnBQjnBT2f8yg7kKPzhYF5zSikejgavwpAY7qw/NcwPf79YlEWhQLvRbXI2tCSkXetV91AHCj2CcARzNVSpOJQgTkwSFFh/MAPzxONnB3ajDsdmBl74Ty/u38hR6i3x0+uJnunq7sOALttkbJWduudK2QqaDWIVoSoOovYNq6RIWO5mHEmgbLqI5PBcUJ5jU8YSL3wm0o8ExUMSsPRl1QahDSR/D3rFi05DGloXrqCXpLSbU1977CatvPMY/sejsXPhptzwSLUZgYTeZ3itC+wevLqzS+4vdKqkfzJPAgMBAAECggEAe5/vTCpvqUTW5QfhZA89mYyMGPY/F7pSoB6cf4RLA3da4gg4UDtwAA2MJejtLoqEguOE8D4T2AEKWULMpatoXUcYGAEEcMssAdHCh8PIW36DqaOhEnFSixkCZNvrgtIFzg0ek/B+PAXhTQ+cMGiL+P0kIcgPTva3SepZnzu3gaHLVAP3iz7JL9L3H61/Hrlum9p06tLyvr2hboz7B3OfcXeg5q7HKkwTo0KZeLPlbZYWvMNWpFegtUvzESSBC2/2n591GF7UhnSOl7J26YJcQ7Du9N/fb8eF5HEjRTyKISg3Slg55zv91YNUyIbDOgMD0TQY2vzIqw3YBQFCxs1GoQKBgQD8ydyfs0RiaLEQLVcWs0RmiJqVkz5M+Zom4nvLWD7TKiOnkYwDanXGRI81htuBEwXkGVibVYD1ORi6OXsyUcyZkfKxDe6n02a00Uf2lmuQb6QKyTa04tAtJIoSnXCh00M8tFj6Sp3vvSClc9Dt+nEl6YNUfWd7NaCBJztHfMbzHwKBgQCocN5Zy3ZoTGiEZ40sStG5YmhnmCVUhh+YMy47oTwBhW/EmCbzUAP3bgQ6RfpUau9P4x7OucTPYDqeycnbW6kpdF1/glVdhmhvcpAG/2nHdD9bfLvqRQoFym+iP7bQDq33JylSSXru9+EFamD2/62pbk5ISaWopL3R2VKevHyK0QKBgQCh2flZgyUwwQlFa1JMZYFRCPu48yq7jEDHztEmVBz9hQiJHKaB9WLtDC6JPgxC61QlRJk385n66JDNg0o+4KLYXNr/xATfDXrAIRMrbgn205Q7l7NgQ6y4iqGg448e/a4aKvqhXq8atYQ2ix3lkSlq9cUxjo14FNDsgJry3ZBgTwKBgH/B/CRHemvpaGzkzeMGbMQbq+zHLalKgbRDPZHk3UqaFwgaD5EE471MKNC6OwDEivcg4UETDf/skQnXK8/BWjEjHbeU94KUa6n8Ttm/6E9sEJInrbAm5U83GC0L/nEYcULA5UqQiTmvsXEWGq29h86255FUqt2a9o6wM0ZK2HvBAoGAYr9XXWRPdkf7W6j1b2btgqKqKe/hD4Q9/LbR/RZ9lWC0Fw84VCl3WZDK9+4+37eutJyl1HSvW5FQRpviizdWXLLPkRsNl0Mgb8DxIysMqtn1X7BsMn5maX40mo2P3aBs++4QftCyklAB0+JIHl1vCUoO4shqocaLkXC3DF3nyGk=";





    /** *//**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 234;

    /** *//**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 256;

    /** *//**
     * <p>
     * 生成密钥对(公钥和私钥)
     * </p>
     *
     * @return
     * @throws Exception
     */
    public static Map<String, Object> genKeyPair() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(2048);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    /** *//**
     * <p>
     * 用私钥对信息生成数字签名
     * </p>
     *
     * @param data 已加密数据
     * @param privateKey 私钥(BASE64编码)
     *
     * @return
     * @throws Exception
     */
    public static String sign(byte[] data, String privateKey) throws Exception {
        byte[] keyBytes = Base64Utils.decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(privateK);
        signature.update(data);
        return Base64Utils.encode(signature.sign());
    }

    /** *//**
     * <p>
     * 校验数字签名
     * </p>
     *
     * @param data 已加密数据
     * @param publicKey 公钥(BASE64编码)
     * @param sign 数字签名
     *
     * @return
     * @throws Exception
     *
     */
    public static boolean verify(byte[] data, String publicKey, String sign)
            throws Exception {
        byte[] keyBytes = Base64Utils.decode(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicK = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(publicK);
        signature.update(data);
        return signature.verify(Base64Utils.decode(sign));
    }

    /** *//**
     * <P>
     * 私钥解密
     * </p>
     *
     * @param encryptedData 已加密数据
     * @param privateKey 私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey)
            throws Exception {
        byte[] keyBytes = Base64Utils.decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }

    /** *//**
     * <p>
     * 公钥解密
     * </p>
     *
     * @param encryptedData 已加密数据
     * @param publicKey 公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPublicKey(byte[] encryptedData, String publicKey)
            throws Exception {
        byte[] keyBytes = Base64Utils.decode(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }

    /** *//**
     * <p>
     * 公钥加密
     * </p>
     *
     * @param data 源数据
     * @param publicKey 公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static String encryptByPublicKey(byte[] data, String publicKey)
            throws Exception {
        byte[] keyBytes = Base64Utils.decode(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();

        String encodedDataStr = new String(Base64.encode(encryptedData));
        String sign = URLEncoder.encode(encodedDataStr, "utf-8");
        return sign;
    }

    /** *//**
     * <p>
     * 私钥加密
     * </p>
     *
     * @param data 源数据
     * @param privateKey 私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(byte[] data, String privateKey)
            throws Exception {
        byte[] keyBytes = Base64Utils.decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

    /** *//**
     * <p>
     * 获取私钥
     * </p>
     *
     * @param keyMap 密钥对
     * @return
     * @throws Exception
     */
    public static String getPrivateKey(Map<String, Object> keyMap)
            throws Exception {
        System.out.println(PRIVATE_KEY);
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return Base64Utils.encode(key.getEncoded());
    }

    /** *//**
     * <p>
     * 获取公钥
     * </p>
     *
     * @param keyMap 密钥对
     * @return
     * @throws Exception
     */
    public static String getPublicKey(Map<String, Object> keyMap)
            throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return Base64Utils.encode(key.getEncoded());
    }






}