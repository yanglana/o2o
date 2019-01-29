package cn.yang.o2o.cache;

import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.SortingParams;
import redis.clients.util.SafeEncoder;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description TODO
 * @Author yanglan
 * @Date 2019/1/24 10:18
 */
public class JedisUtil {
    //缓存生存时间
    private final int expire = 60000;
    //缓存生存时间
    public Keys KEYS;
    //对存储结构为String类型的操作
    public Strings STRINGS;
    //对存储结构为List类型的操作
    public Lists LISTS;
    //对存储结构为Set类型的操作
    public Sets SETS;
    //对存储结构为HashMap类型的操作
    public Hash HASH;

    //Redis连接池对象
    private JedisPool jedisPool;

    /*
     * @Description 获取redis连接池
     * @Param []
     * @Return redis.clients.jedis.JedisPool
     */
    public JedisPool getJedisPool() {
        return jedisPool;
    }

    /*
     * @Description 设置redis连接池
     * @Param [jedisPool]
     * @Return void
     */
    public void setJedisPool(JedisPoolWriper jedisPoolWriper) {
        this.jedisPool = jedisPoolWriper.getJedisPool();
    }

    /*
     * @Description 从jedis连接池中获取获取jedis对象
     * @Param []
     * @Return redis.clients.jedis.Jedis
     */
    public Jedis getJedis() {
        return jedisPool.getResource();
    }

    /*
     * @Description 设置过期时间
     * @Param [key, seconds]
     * @Return void
     */
    public void expire(String key, int seconds) {
        if (seconds <= 0) {
            return;
        }
        Jedis jedis = getJedis();
        jedis.expire(key, seconds);
        jedis.close();
    }

    /*
     * @Description 设置默认过期时间
     * @Param [key]
     * @Return void
     */
    public void expire(String key) {
        expire(key, expire);
    }

    // *****************************Keys*********************************
    public class Keys {

        /*
         * @Description 清空所有key
         * @Param []
         * @Return java.lang.String
         */
        public String flushAll() {
            Jedis jedis = getJedis();
            String status = jedis.flushAll();
            jedis.close();
            return status;
        }

        /*
         * @Description 更改key
         * @Param [oldkey, newkey]
         * @Return java.lang.String
         */
        public String rename(String oldkey, String newkey) {
            return rename(SafeEncoder.encode(oldkey), SafeEncoder.encode(newkey));
        }

        /*
         * @Description 更改key,仅当新key不存在时才执行
         * @Param [oldkey, newkey]
         * @Return long
         */
        public long renamenx(String oldkey, String newkey) {
            Jedis jedis = getJedis();
            long status = jedis.renamenx(oldkey, newkey);
            jedis.close();
            return status;
        }

        /*
         * @Description 更改key
         * @Param [encode, encode1]
         * @Return java.lang.String
         */
        private String rename(byte[] oldkey, byte[] newkey) {
            Jedis jedis = getJedis();
            String status = jedis.rename(oldkey, newkey);
            jedis.close();
            return status;
        }

        /*
         * @Description 设置key的过期时间，以秒为单位
         * @Param [key, seconds]
         * @Return long
         */
        public long expired(String key, int seconds) {
            Jedis jedis = getJedis();
            long count = jedis.expire(key, seconds);
            jedis.close();
            return count;
        }

        /*
         * @Description 设置key的过期时间,它是距历元（即格林威治标准时间 1970 年 1 月 1 日的 00:00:00，格里高利历）的偏移量。
         * @Param [key, timestamp]
         * @Return long
         */
        public long expireAt(String key, long timestamp) {
            Jedis jedis = getJedis();
            long count = jedis.expireAt(key, timestamp);
            jedis.close();
            return count;
        }

        /*
         * @Description 查询key的过期时间
         * @Param [key]
         * @Return long
         */
        public long ttl(String key) {
            //ShardedJedis sjedis = getShardedJedis();
            Jedis sjedis = getJedis();
            long len = sjedis.ttl(key);
            sjedis.close();
            return len;
        }

        /*
         * @Description 取消对key过期时间的设置
         * @Param [key]
         * @Return long
         */
        public long persist(String key) {
            Jedis jedis = getJedis();
            long count = jedis.persist(key);
            jedis.close();
            return count;
        }

        /*
         * @Description 删除keys对应的记录,可以是多个key
         * @Param [keys]
         * @Return long
         */
        public long del(String... keys) {
            Jedis jedis = getJedis();
            long count = jedis.del(keys);
            jedis.close();
            return count;
        }

        /*
         * @Description 删除keys对应的记录,可以是多个key
         * @Param [keys]
         * @Return long
         */
        public long del(byte[]... keys) {
            Jedis jedis = getJedis();
            long count = jedis.del(keys);
            jedis.close();
            return count;
        }

        /*
         * @Description 判断key是否存在
         * @Param [key]
         * @Return boolean
         */
        public boolean exists(String key) {
            Jedis sjedis = getJedis();
            boolean exis = sjedis.exists(key);
            sjedis.close();
            return exis;
        }

        /*
         * @Description 对List,Set,SortSet进行排序,如果集合数据较大应避免使用这个方法
         * @Param [key]
         * @Return java.util.List<java.lang.String>
         */
        public List<String> sort(String key) {
            Jedis sjedis = getJedis();
            List<String> list = sjedis.sort(key);
            sjedis.close();
            return list;
        }

        /*
         * @Description 对List,Set,SortSet进行排序或limit
         * @Param [key, parame]
         * @Return java.util.List<java.lang.String>
         */
        public List<String> sort(String key, SortingParams parame) {
            Jedis sjedis = getJedis();
            List<String> list = sjedis.sort(key, parame);
            sjedis.close();
            return list;
        }

        /*
         * @Description 返回指定key存储的类型
         * @Param [key]
         * @Return java.lang.String
         */
        public String type(String key) {
            Jedis sjedis = getJedis();
            String type = sjedis.type(key);
            sjedis.close();
            return type;
        }

        /*
         * @Description 查找所有匹配给定的模式的键
         * @Param [pattern] key的表达式,*表示多个，？表示一个
         * @Return java.util.Set<java.lang.String>
         */
        public Set<String> keys(String pattern) {
            Jedis jedis = getJedis();
            Set<String> set = jedis.keys(pattern);
            jedis.close();
            return set;
        }
    }

    // *****************************Strings*************************************
    public class Strings {
        /*
         * @Description 根据key获取记录
         * @Param [key]
         * @Return java.lang.String
         */
        public String get(String key) {
            Jedis sjedis = getJedis();
            String value = sjedis.get(key);
            sjedis.close();
            return value;
        }

        /*
         * @Description 根据key获取记录
         * @Param [key]
         * @Return byte[]
         */
        public byte[] get(byte[] key) {
            Jedis sjedis = getJedis();
            byte[] value = sjedis.get(key);
            sjedis.close();
            return value;
        }

        /*
         * @Description 添加记录,如果记录已存在将覆盖原有的value
         * @Param [key, value]
         * @Return java.lang.String
         */
        public String set(String key, String value) {
            return set(SafeEncoder.encode(key), SafeEncoder.encode(value));
        }

        /*
         * @Description 添加记录,如果记录已存在将覆盖原有的value
         * @Param [encode, encode1]
         * @Return java.lang.String
         */
        private String set(byte[] key, byte[] value) {
            Jedis jedis = getJedis();
            String status = jedis.set(key, value);
            jedis.close();
            return status;
        }

        /*
         * @Description 添加记录,如果记录已存在将覆盖原有的value
         * @Param [key, value]
         * @Return java.lang.String
         */
        public String set(String key, byte[] value) {
            return set(SafeEncoder.encode(key), value);
        }

        /*
         * @Description 添加有过期时间的记录
         * @Param [key, seconds, value]
         * @Return java.lang.String
         */
        public String setEx(String key, int seconds, String value) {
            Jedis jedis = getJedis();
            String str = jedis.setex(key, seconds, value);
            jedis.close();
            return str;
        }

        /*
         * @Description 添加有过期时间的记录
         * @Param [key, seconds, value]
         * @Return java.lang.String
         */
        public String setEx(byte[] key, int seconds, byte[] value) {
            Jedis jedis = getJedis();
            String str = jedis.setex(key, seconds, value);
            jedis.close();
            return str;
        }

        /*
         * @Description 添加一条记录，仅当给定的key不存在时才插入
         * @Param [key, value]
         * @Return long
         */
        public long setnx(String key, String value) {
            Jedis jedis = getJedis();
            long str = jedis.setnx(key, value);
            jedis.close();
            return str;
        }

        /*
         * @Description 从指定位置开始插入数据，插入的数据会覆盖指定位置以后的数据<br/>
         * 例:String str1="123456789";<br/>
         * 对str1操作后setRange(key,4,0000)，str1="123400009";
         * @Param [key, offset, value]
         * @Return long
         */
        public long setRange(String key, long offset, String value) {
            Jedis jedis = getJedis();
            long len = jedis.setrange(key, offset, value);
            jedis.close();
            return len;
        }

        /*
         * @Description 在指定的key中追加value
         * @Param [key, value]
         * @Return long
         */
        public long append(String key, String value) {
            Jedis jedis = getJedis();
            long len = jedis.append(key, value);
            jedis.close();
            return len;
        }

        /*
         * @Description 将key对应的value减去指定的值，只有value可以转为数字时该方法才可用
         * @Param [key, number]
         * @Return long
         */
        public long decrBy(String key, long number) {
            Jedis jedis = getJedis();
            long len = jedis.decrBy(key, number);
            jedis.close();
            return len;
        }

        /*
         * @Description <b>可以作为获取唯一id的方法</b><br/>
         * 将key对应的value加上指定的值，只有value可以转为数字时该方法才可用
         * @Param [key, number]
         * @Return long
         */
        public long incrBy(String key, long number) {
            Jedis jedis = getJedis();
            long len = jedis.incrBy(key, number);
            jedis.close();
            return len;
        }

        /*
         * @Description 对指定key对应的value进行截取
         * @Param [key, startOffset, endOffset]
         * @Return java.lang.String
         */
        public String getrange(String key, long startOffset, long endOffset) {
            // ShardedJedis sjedis = getShardedJedis();
            Jedis sjedis = getJedis();
            String value = sjedis.getrange(key, startOffset, endOffset);
            sjedis.close();
            return value;
        }

        /*
         * @Description 获取并设置指定key对应的value<br/>
         * 如果key存在返回之前的value,否则返回null
         * @Param [key, value]
         * @Return java.lang.String
         */
        public String getSet(String key, String value) {
            Jedis jedis = getJedis();
            String str = jedis.getSet(key, value);
            jedis.close();
            return str;
        }

        /*
         * @Description 批量获取记录,如果指定的key不存在返回List的对应位置将是null
         * @Param [keys]
         * @Return java.util.List<java.lang.String>
         */
        public List<String> mget(String... keys) {
            Jedis jedis = getJedis();
            List<String> str = jedis.mget(keys);
            jedis.close();
            return str;
        }

        /*
         * @Description 批量存储记录
         * @Param [keysvalues]
         * @Return java.lang.String
         */
        public String mset(String... keysvalues) {
            Jedis jedis = getJedis();
            String str = jedis.mset(keysvalues);
            jedis.close();
            return str;
        }

        /*
         * @Description 获取key对应的值的长度
         * @Param [key]
         * @Return long
         */
        public long strlen(String key) {
            Jedis jedis = getJedis();
            long len = jedis.strlen(key);
            jedis.close();
            return len;
        }
    }

    // *****************************Sets************************************
    public class Sets {
        /*
         * @Description 向Set添加一条记录，如果member已存在返回0,否则返回1
         * @Param [key, member]
         * @Return long
         */
        public long sadd(String key, String member) {
            Jedis jedis = getJedis();
            long s = jedis.sadd(key, member);
            jedis.close();
            return s;
        }

        public long sadd(byte[] key, byte[] member) {
            Jedis jedis = getJedis();
            long s = jedis.sadd(key, member);
            jedis.close();
            return s;
        }

        /*
         * @Description 获取给定key中元素个数
         * @Param [key]
         * @Return long
         */
        public long scard(String key) {
            // ShardedJedis sjedis = getShardedJedis();
            Jedis sjedis = getJedis();
            long len = sjedis.scard(key);
            sjedis.close();
            return len;
        }

        /*
         * @Description 返回从第一组和所有的给定集合之间的差异的成员
         * @Param [keys]
         * @Return java.util.Set<java.lang.String>
         */
        public Set<String> sdiff(String... keys) {
            Jedis jedis = getJedis();
            Set<String> set = jedis.sdiff(keys);
            jedis.close();
            return set;
        }

        /*
         * @Description 这个命令等于sdiff,但返回的不是结果集,而是将结果集存储在新的集合中，如果目标已存在，则覆盖。
         * @Param [newkey, keys]  新结果集的key ;比较的集合
         * @Return long  新集合中的记录数
         */
        public long sdiffstore(String newkey, String... keys) {
            Jedis jedis = getJedis();
            long s = jedis.sdiffstore(newkey, keys);
            jedis.close();
            return s;
        }

        /*
         * @Description 返回给定集合交集的成员,如果其中一个集合为不存在或为空，则返回空Set
         * @Param [keys]
         * @Return java.util.Set<java.lang.String>
         */
        public Set<String> sinter(String... keys) {
            Jedis jedis = getJedis();
            Set<String> set = jedis.sinter(keys);
            jedis.close();
            return set;
        }

        /*
         * @Description 这个命令等于sinter,但返回的不是结果集,而是将结果集存储在新的集合中，如果目标已存在，则覆盖。
         * @Param [newkey, keys]
         * @Return long
         */
        public long sinterstore(String newkey, String... keys) {
            Jedis jedis = getJedis();
            long s = jedis.sinterstore(newkey, keys);
            jedis.close();
            return s;
        }

        /*
         * @Description 确定一个给定的值是否存在
         * @Param [key, member]
         * @Return boolean
         */
        public boolean sismember(String key, String member) {
            // ShardedJedis sjedis = getShardedJedis();
            Jedis sjedis = getJedis();
            boolean s = sjedis.sismember(key, member);
            sjedis.close();
            return s;
        }

        /*
         * @Description 返回集合中的所有成员
         * @Param [key]
         * @Return java.util.Set<java.lang.String>
         */
        public Set<String> smembers(String key) {
            // ShardedJedis sjedis = getShardedJedis();
            Jedis sjedis = getJedis();
            Set<String> set = sjedis.smembers(key);
            sjedis.close();
            return set;
        }

        public Set<byte[]> smembers(byte[] key) {
            // ShardedJedis sjedis = getShardedJedis();
            Jedis sjedis = getJedis();
            Set<byte[]> set = sjedis.smembers(key);
            sjedis.close();
            return set;
        }

        /*
         * @Description 将成员从源集合移出放入目标集合 <br/>
         * 如果源集合不存在或不包哈指定成员，不进行任何操作，返回0<br/>
         * 否则该成员从源集合上删除，并添加到目标集合，如果目标集合中成员已存在，则只在源集合进行删除
         * @Param [srckey: 源集合
         *          dstkey: 目标集合
         *          member: 源集合中的成员]
         * @Return long 状态码，1成功，0失败
         */
        public long smove(String srckey, String dstkey, String member) {
            Jedis jedis = getJedis();
            long s = jedis.smove(srckey, dstkey, member);
            jedis.close();
            return s;
        }

        /*
         * @Description 从集合中删除成员
         * @Param [key]
         * @Return java.lang.String 被删除的成员
         */
        public String spop(String key) {
            Jedis jedis = getJedis();
            String s = jedis.spop(key);
            jedis.close();
            return s;
        }

        /*
         * @Description 从集合中删除指定成员
         * @Param [key, member]
         * @Return long
         */
        public long srem(String key, String member) {
            Jedis jedis = getJedis();
            long s = jedis.srem(key, member);
            jedis.close();
            return s;
        }

        /*
         * @Description 合并多个集合并返回合并后的结果，合并后的结果集合并不保存<br/>
         * @Param [keys]
         * @Return java.util.Set<java.lang.String>
         */
        public Set<String> sunion(String... keys) {
            Jedis jedis = getJedis();
            Set<String> set = jedis.sunion(keys);
            jedis.close();
            return set;
        }

        /*
         * @Description 合并多个集合并将合并后的结果集保存在指定的新集合中，如果新集合已经存在则覆盖
         * @Param [newkey, keys]
         * @Return long
         */
        public long sunionstore(String newkey, String... keys) {
            Jedis jedis = getJedis();
            long s = jedis.sunionstore(newkey, keys);
            jedis.close();
            return s;
        }
    }

    // *****************************Hash************************************
    public class Hash {
        /*
         * @Description 从hash中删除指定的存储
         * @Param [key, fieid]
         * @Return long
         */
        public long hdel(String key, String fieid) {
            Jedis jedis = getJedis();
            long s = jedis.hdel(key, fieid);
            jedis.close();
            return s;
        }

        public long hdel(String key) {
            Jedis jedis = getJedis();
            long s = jedis.del(key);
            jedis.close();
            return s;
        }

        /*
         * @Description 测试hash中指定的存储是否存在
         * @Param [key, fieid]
         * @Return boolean
         */
        public boolean hexists(String key, String fieid) {
            // ShardedJedis sjedis = getShardedJedis();
            Jedis sjedis = getJedis();
            boolean s = sjedis.hexists(key, fieid);
            sjedis.close();
            return s;
        }

        /*
         * @Description 返回hash中指定存储位置的值
         * @Param [key, fieid]
         * @Return java.lang.String
         */
        public String hget(String key, String fieid) {
            // ShardedJedis sjedis = getShardedJedis();
            Jedis sjedis = getJedis();
            String s = sjedis.hget(key, fieid);
            sjedis.close();
            return s;
        }

        public byte[] hget(byte[] key, byte[] fieid) {
            // ShardedJedis sjedis = getShardedJedis();
            Jedis sjedis = getJedis();
            byte[] s = sjedis.hget(key, fieid);
            sjedis.close();
            return s;
        }

        /*
         * @Description 以Map的形式返回hash中的存储和值
         * @Param [key]
         * @Return java.util.Map<java.lang.String,java.lang.String>
         */
        public Map<String, String> hgetAll(String key) {
            // ShardedJedis sjedis = getShardedJedis();
            Jedis sjedis = getJedis();
            Map<String, String> map = sjedis.hgetAll(key);
            sjedis.close();
            return map;
        }

        /*
         * @Description 添加一个对应关系
         * @Param [key, fieid, value]
         * @Return long
         */
        public long hset(String key, String fieid, String value) {
            Jedis jedis = getJedis();
            long s = jedis.hset(key, fieid, value);
            jedis.close();
            return s;
        }

        public long hset(String key, String fieid, byte[] value) {
            Jedis jedis = getJedis();
            long s = jedis.hset(key.getBytes(), fieid.getBytes(), value);
            jedis.close();
            return s;
        }

        /*
         * @Description 添加对应关系，只有在fieid不存在时才执行
         * @Param [key, fieid, value]
         * @Return long
         */
        public long hsetnx(String key, String fieid, String value) {
            Jedis jedis = getJedis();
            long s = jedis.hsetnx(key, fieid, value);
            jedis.close();
            return s;
        }

        /*
         * @Description 获取hash中value的集合
         * @Param [key]
         * @Return java.util.List<java.lang.String>
         */
        public List<String> hvals(String key) {
            // ShardedJedis sjedis = getShardedJedis();
            Jedis sjedis = getJedis();
            List<String> list = sjedis.hvals(key);
            sjedis.close();
            return list;
        }

        /*
         * @Description 在指定的存储位置加上指定的数字，存储位置的值必须可转为数字类型
         * @Param [key, fieid, value]
         * @Return long
         */
        public long hincrby(String key, String fieid, long value) {
            Jedis jedis = getJedis();
            long s = jedis.hincrBy(key, fieid, value);
            jedis.close();
            return s;
        }

        /*
         * @Description 返回指定hash中的所有存储名字,类似Map中的keySet方法
         * @Param [key]
         * @Return java.util.Set<java.lang.String>
         */
        public Set<String> hkeys(String key) {
            // ShardedJedis sjedis = getShardedJedis();
            Jedis sjedis = getJedis();
            Set<String> set = sjedis.hkeys(key);
            sjedis.close();
            return set;
        }

        /*
         * @Description 获取hash中存储的个数，类似Map中size方法
         * @Param [key]
         * @Return long
         */
        public long hlen(String key) {
            // ShardedJedis sjedis = getShardedJedis();
            Jedis sjedis = getJedis();
            long len = sjedis.hlen(key);
            sjedis.close();
            return len;
        }

        /*
         * @Description 根据多个key，获取对应的value，返回List,如果指定的key不存在,List对应位置为null
         * @Param [key, fieids]
         * @Return java.util.List<java.lang.String>
         */
        public List<String> hmget(String key, String... fieids) {
            // ShardedJedis sjedis = getShardedJedis();
            Jedis sjedis = getJedis();
            List<String> list = sjedis.hmget(key, fieids);
            sjedis.close();
            return list;
        }

        public List<byte[]> hmget(byte[] key, byte[]... fieids) {
            // ShardedJedis sjedis = getShardedJedis();
            Jedis sjedis = getJedis();
            List<byte[]> list = sjedis.hmget(key, fieids);
            sjedis.close();
            return list;
        }

        /*
         * @Description 添加对应关系，如果对应关系已存在，则覆盖
         * @Param [key, map]
         * @Return java.lang.String
         */
        public String hmset(String key, Map<String, String> map) {
            Jedis jedis = getJedis();
            String s = jedis.hmset(key, map);
            jedis.close();
            return s;
        }

        public String hmset(byte[] key, Map<byte[], byte[]> map) {
            Jedis jedis = getJedis();
            String s = jedis.hmset(key, map);
            jedis.close();
            return s;
        }
    }

    // *****************************Lists************************************
    public class Lists {
        /*
         * @Description List长度
         * @Param [key]
         * @Return long
         */
        public long llen(String key) {
            return llen(SafeEncoder.encode(key));
        }

        public long llen(byte[] key) {
            Jedis sjedis = getJedis();
            long count = sjedis.llen(key);
            sjedis.close();
            return count;
        }

        /*
         * @Description 覆盖操作,将覆盖List中指定位置的值
         * @Param [key, index, value]
         * @Return java.lang.String
         */
        public String lset(byte[] key, int index, byte[] value) {
            Jedis jedis = getJedis();
            String status = jedis.lset(key, index, value);
            jedis.close();
            return status;
        }

        public String lset(String key, int index, String value) {
            return lset(SafeEncoder.encode(key), index, SafeEncoder.encode(value));
        }

        /*
         * @Description 在value的相对位置插入记录
         * @Param [key, where, pivot, value]
         * @Return long
         */
        public long linsert(String key, LIST_POSITION where, String pivot, String value) {
            return linsert(SafeEncoder.encode(key), where, SafeEncoder.encode(pivot), SafeEncoder.encode(value));
        }

        /*
         * @Description 在指定位置插入记录
         * @Param [key, where, prvot, value]
         * @Return long
         */
        public long linsert(byte[] key, LIST_POSITION where, byte[] pivot, byte[] value) {
            Jedis jedis = getJedis();
            long count = jedis.linsert(key, where, pivot, value);
            jedis.close();
            return count;
        }

        /*
         * @Description 获取List中指定位置的值
         * @Param [key, index]
         * @Return java.lang.String
         */
        public String lindex(String key, int index) {
            return SafeEncoder.encode(lindex(SafeEncoder.encode(key), index));
        }

        public byte[] lindex(byte[] key, int index) {
            // ShardedJedis sjedis = getShardedJedis();
            Jedis sjedis = getJedis();
            byte[] value = sjedis.lindex(key, index);
            sjedis.close();
            return value;
        }

        /*
         * @Description 将List中的第一条记录移出List
         * @Param [key]
         * @Return java.lang.String
         */
        public String lpop(String key) {
            return SafeEncoder.encode(lpop(SafeEncoder.encode(key)));
        }

        public byte[] lpop(byte[] key) {
            Jedis jedis = getJedis();
            byte[] value = jedis.lpop(key);
            jedis.close();
            return value;
        }

        /*
         * @Description 将List中最后第一条记录移出List
         * @Param [key]
         * @Return java.lang.String
         */
        public String rpop(String key) {
            Jedis jedis = getJedis();
            String value = jedis.rpop(key);
            jedis.close();
            return value;
        }

        /*
         * @Description 向List尾部追加记录
         * @Param [key, value]
         * @Return long
         */
        public long lpush(String key, String value) {
            return lpush(SafeEncoder.encode(key), SafeEncoder.encode(value));
        }

        public long lpush(byte[] key, byte[] value) {
            Jedis jedis = getJedis();
            long count = jedis.lpush(key, value);
            jedis.close();
            return count;
        }

        /*
         * @Description 向List头部追加记录
         * @Param [key, value]
         * @Return long
         */
        public long rpush(String key, String value) {
            Jedis jedis = getJedis();
            long count = jedis.rpush(key, value);
            jedis.close();
            return count;
        }

        public long rpush(byte[] key, byte[] value) {
            Jedis jedis = getJedis();
            long count = jedis.rpush(key, value);
            jedis.close();
            return count;
        }

        /*
         * @Description 获取指定范围的记录，可以做为分页使用
         * @Param [key, start, end]
         * @Return java.util.List<java.lang.String>
         */
        public List<String> lrange(String key, long start, long end) {
            // ShardedJedis sjedis = getShardedJedis();
            Jedis sjedis = getJedis();
            List<String> list = sjedis.lrange(key, start, end);
            sjedis.close();
            return list;
        }

        /*
         * @Description 获取指定范围的记录，可以做为分页使用
         * @Param [key, start, end]
         * @Return java.util.List<byte[]>
         */
        public List<byte[]> lrange(byte[] key, int start, int end) {
            // ShardedJedis sjedis = getShardedJedis();
            Jedis sjedis = getJedis();
            List<byte[]> list = sjedis.lrange(key, start, end);
            sjedis.close();
            return list;
        }

        /*
         * @Description 删除List中c条记录，被删除的记录值为value
         * @Param [key,
         *         c: 要删除的数量，如果为负数则从List的尾部检查并删除符合的记录
         *         value: 要匹配的值]
         * @Return long 删除后的List中的记录数
         */
        public long lrem(byte[] key, int c, byte[] value) {
            Jedis jedis = getJedis();
            long count = jedis.lrem(key, c, value);
            jedis.close();
            return count;
        }

        public long lrem(String key, int c, String value) {
            return lrem(SafeEncoder.encode(key), c, SafeEncoder.encode(value));
        }

        /*
         * @Description 算是删除吧，只保留start与end之间的记录
         * @Param [key,
         *          start: start 记录的开始位置(0表示第一条记录)
         *         end: 记录的结束位置（如果为-1则表示最后一个，-2，-3以此类推）]
         * @Return java.lang.String
         */
        public String ltrim(byte[] key, int start, int end) {
            Jedis jedis = getJedis();
            String str = jedis.ltrim(key, start, end);
            jedis.close();
            return str;
        }

        public String ltrim(String key, int start, int end) {
            return ltrim(SafeEncoder.encode(key), start, end);
        }
    }
}
