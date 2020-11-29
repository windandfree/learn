import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @JIRA:HY3-
 * @Des:单例模式 生产jedispool操作类
 * @Author:WL
 * @Date: 15:20 2020/11/28
 */
public class JedisPoolUtil {
    /**
     * 懒汉单例模式，饿了才去准备食物
     * 在这里new一个JedisPool，饿汉单例模式，先准备好食物，饿了就吃，怕饿。
     */
    private static volatile JedisPool jedisPool = null;

    /**
     * 私有化构造方法，不能直接new
     */
    private JedisPoolUtil(){}

    /**
     * 对外暴露获取jedisPool的实例方法
     * @return
     */
    public static JedisPool getJedisPoolInstance(){
        if(null == jedisPool){
            synchronized (JedisPoolUtil.class){
                if(null==jedisPool){
                    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
                    jedisPoolConfig.setMaxTotal(8);//最大实例数
                    jedisPoolConfig.setMaxIdle(1);//允许空闲实例数
                    jedisPoolConfig.setMaxWaitMillis(10*1000);//最大等待时间
                    jedisPoolConfig.setTestOnBorrow(true);//借用一个jedis实例的时候检查连接可用性，（ping()）
                    jedisPoolConfig.setBlockWhenExhausted(true);//pool中实例使用完时进行阻塞
                    jedisPool = new JedisPool(jedisPoolConfig,"192.168.1.115",6379);
                }
            }
        }
        return jedisPool;
    }

    /**
     * 释放资源
     * @param jedisPool
     */
    public static void release(JedisPool jedisPool){
        if(null != jedisPool){
            jedisPool.destroy();
        }
    }


}
