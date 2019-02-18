package cn.yang.o2o.cache;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Description 强指定redis的JedisPool接口构造函数，这样才能在centos成功创建jedispool
 * @Author yanglan
 * @Date 2019/1/24 9:47
 */
public class JedisPoolWriper {
    /**
     * Redis连接池对象
     */
    private JedisPool jedisPool;

    public JedisPoolWriper(final JedisPoolConfig poolConfig, final String host, final int port) {
        try {
            jedisPool = new JedisPool(poolConfig, host, port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * @Description 获取Redis连接池对象
     * @Param []
     * @Return redis.clients.jedis.JedisPool
     */
    public JedisPool getJedisPool() {
        return jedisPool;
    }

    /*
     * @Description 注入Redis连接池对象
     * @Param [jedisPool]
     * @Return void
     */
    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }
}
