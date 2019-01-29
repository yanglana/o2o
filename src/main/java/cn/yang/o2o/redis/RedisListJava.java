package cn.yang.o2o.redis;

import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @Description TODO
 * @Author yanglan
 * @Date 2019/1/28 16:46
 */
public class RedisListJava {
    public static void main(String[] args) {
        //Connecting to Redis server on localhost int a=1;
        Jedis jedis = new Jedis("127.0.0.1");
        jedis.auth("123456");
        System.out.println("Connection to server sucessfully");
        //store data in redis list jedis.lpush("tutorial-list", "Redis");
        jedis.lpush("tutorial-list", "Mongodb111");
        jedis.lpush("tutorial-list", "Mysql");
        // Get the stored data and print it
        if (jedis.isConnected()) {
            List<String> list = jedis.lrange("tutorial-list", 0, 5);
            for (int i = 0; i < list.size(); i++) {
                System.out.println("Stored string in redis:: " + list.get(i));
            }
        }
    }
}
