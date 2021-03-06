//package com.bjpowernode.springboot.service.impl;
//
//import cn.hutool.core.date.DatePattern;
//import cn.hutool.core.date.DateUtil;
//import com.bjpowernode.springboot.common.utils.FastJson2JsonRedisSerializer;
//import com.bjpowernode.springboot.service.RedisService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.*;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.PostConstruct;
//import java.io.Serializable;
//import java.util.Date;
//import java.util.List;
//import java.util.Set;
//import java.util.concurrent.TimeUnit;
//
///**
// * @author xb
// * @Description: redis调用(单机版)
// */
//@Service
//public class RedisServiceImpl implements RedisService {
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    /**
//     * 初始化之后需要执行的方法  构造方法 > @Autowired > @PostConstruct
//     */
//    @PostConstruct
//    public void setRedisTemplate() {
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new FastJson2JsonRedisSerializer(Object.class));
//    }
//
//    /**
//     * 写入缓存
//     *
//     * @param key
//     * @param value
//     * @return
//     */
//    @Override
//    public boolean set(final String key, Object value) {
//        boolean result = false;
//        try {
//            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
//            operations.set(key, value);
//            result = true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//
//    /**
//     * 写入缓存设置时效时间
//     *
//     * @param key
//     * @param value
//     * @return
//     */
//    @Override
//    public boolean set(final String key, Object value, Long expireTime, TimeUnit timeUnit) {
//        boolean result = false;
//        try {
//            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
//            operations.set(key, value);
//            redisTemplate.expire(key, expireTime, timeUnit);
//            result = true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    /**
//     * 批量删除对应的value
//     *
//     * @param keys
//     */
//    @Override
//    public void remove(final String... keys) {
//        for (String key : keys) {
//            remove(key);
//        }
//    }
//
//    /**
//     * 批量删除key
//     *
//     * @param pattern
//     */
//    @Override
//    public void removePattern(final String pattern) {
//        Set<Serializable> keys = redisTemplate.keys(pattern);
//        if (keys.size() > 0) {
//            redisTemplate.delete(keys);
//        }
//    }
//
//    /**
//     * 删除对应的value
//     *
//     * @param key
//     */
//    @Override
//    public void remove(final String key) {
//        if (exists(key)) {
//            redisTemplate.delete(key);
//        }
//    }
//
//    /**
//     * 判断缓存中是否有对应的value
//     *
//     * @param key
//     * @return
//     */
//    @Override
//    public boolean exists(final String key) {
//        return redisTemplate.hasKey(key);
//    }
//
//    /**
//     * 读取缓存
//     *
//     * @param key
//     * @return
//     */
//    @Override
//    public Object get(final String key) {
//        Object result = null;
//        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
//        result = operations.get(key);
//        return result;
//    }
//
//    /**
//     * 哈希 添加
//     *
//     * @param key
//     * @param hashKey
//     * @param value
//     */
//    @Override
//    public void hmSet(String key, Object hashKey, Object value) {
//        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
//        hash.put(key, hashKey, value);
//    }
//
//    /**
//     * 哈希获取数据
//     *
//     * @param key
//     * @param hashKey
//     * @return
//     */
//    @Override
//    public Object hmGet(String key, Object hashKey) {
//        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
//        return hash.get(key, hashKey);
//    }
//
//    /**
//     * 列表添加
//     *
//     * @param k
//     * @param v
//     */
//    @Override
//    public void lPush(String k, Object v) {
//        ListOperations<String, Object> list = redisTemplate.opsForList();
//        list.rightPush(k, v);
//    }
//
//    /**
//     * 列表获取
//     *
//     * @param k
//     * @param l
//     * @param l1
//     * @return
//     */
//    @Override
//    public List<Object> lRange(String k, long l, long l1) {
//        ListOperations<String, Object> list = redisTemplate.opsForList();
//        return list.range(k, l, l1);
//    }
//
//    /**
//     * 集合添加
//     *
//     * @param key
//     * @param value
//     */
//    @Override
//    public void add(String key, Object value) {
//        SetOperations<String, Object> set = redisTemplate.opsForSet();
//        set.add(key, value);
//    }
//
//    /**
//     * 集合获取
//     *
//     * @param key
//     * @return
//     */
//    @Override
//    public Set<Object> setMembers(String key) {
//        SetOperations<String, Object> set = redisTemplate.opsForSet();
//        return set.members(key);
//    }
//
//    /**
//     * 有序集合添加
//     *
//     * @param key
//     * @param value
//     * @param scoure
//     */
//    @Override
//    public void zAdd(String key, Object value, double scoure) {
//        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
//        zset.add(key, value, scoure);
//    }
//
//    /**
//     * 有序集合获取
//     *
//     * @param key
//     * @param scoure
//     * @param scoure1
//     * @return
//     */
//    @Override
//    public Set<Object> rangeByScore(String key, double scoure, double scoure1) {
//        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
//        return zset.rangeByScore(key, scoure, scoure1);
//    }
//
//
//    /**
//     * 自增加1
//     */
//    @Override
//    public long incr(String key) {
//        return redisTemplate.opsForValue().increment(key);
//
//    }
//
//
//    /**
//     * 按key生成每天的自增序列
//     */
//    @Override
//    public String incrSerial(String tag) {
//        String date = DateUtil.format(new Date(), DatePattern.PURE_DATE_PATTERN);
//        String serial = tag + ":" + date;
//        String sequence = getSequence(incr(serial));
//        StringBuilder sb = new StringBuilder();
//        sb.append(date).append(sequence);
//        return tag + sb.toString();
//    }
//
//    public static String getSequence(long seq) {
//        String str = String.valueOf(seq);
//        int len = str.length();
//        if (len >= 5) {// 取决于业务规模,应该不会到达5
//            return str;
//        }
//        int rest = 5 - len;
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < rest; i++) {
//            sb.append('0');
//        }
//        sb.append(str);
//        return sb.toString();
//    }
//
//}
