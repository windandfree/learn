import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.util.List;

/**
 * @JIRA:HY3-
 * @Des:测试redis的连通性
 * @Author:WL
 * @Date: 19:28 2020/11/26
 */
public class RedisPingTest {
    public static void main(String[] args) throws InterruptedException {
        Jedis jedis = new Jedis("192.168.1.115",6379);
        System.out.println(jedis.ping());
        //jedis.auth("helloworld");  //开启密码验证（配置文件中为 requirepass helloworld）的时候需要执行该方法
    }
}
