package org.tinygame.herostory.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tinygame.herostory.cmdHandler.UserLoginCmdHandler;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Redis 工具类
 */
public final class RedisUtil {

    /**
     * 日志对象
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisUtil.class);

    /**
     * Redis连接池
     */
    private static JedisPool jedisPool = null;

    /**
     * 私有化默认构造器
     */
    private RedisUtil(){}

    /**
     * 初始化
     */
    public static void init(){
        try {
            jedisPool = new JedisPool("192.168.163.100",6379);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e);
        }
    }

    /**
     * 获取jedis实例
     * @return jedis 实例
     */
    public static Jedis getJedis(){
        if (null == jedisPool) {
            LOGGER.error("jedisPool 未初始化");
            throw new RuntimeException("jedisPool 未初始化");
        }

        Jedis redis = jedisPool.getResource();
        if (null == redis){
            LOGGER.error("获取不到redis,redis={}",redis);
            return null;
        }
        return redis;
    }
}
