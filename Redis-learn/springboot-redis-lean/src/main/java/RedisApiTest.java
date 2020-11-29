import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Des:Redis常用API
 * @Author:WL
 * @Date: 14:41 2020/11/28
 */
public class RedisApiTest {
    public static void main(String[] args) {
         Jedis jedis = new Jedis("192.168.1.115",6379);
        //jedis.auth("helloworld");  //开启密码验证（配置文件中为 requirepass helloworld）的时候需要执行该方法

        //key
        System.out.println("=========================Key=========================");
        Set<String> keys = jedis.keys("*");
        System.out.println("keys * +    "+keys.toString());
        System.out.println("jedis.get  +    "+jedis.get("k2"));
        System.out.println("jedis.exists  +    "+jedis.exists("k1"));
        System.out.println("jedis.ttl  +    "+jedis.ttl("k1"));

        //String
        System.out.println("=========================String=========================");
        jedis.set("str3","strv3");
        jedis.mset("str4","strv4","str5","strv5");
        List<String> mget = jedis.mget("str3", "str4", "str5");
        System.out.println(mget.toString());

        //List
        System.out.println("=========================List=========================");
        jedis.lpush("myList","v1","v2","v3");//从头部进入
        List<String> myList = jedis.lrange("myList", 0L, -1L);
        System.out.println(myList.toString());

        //Hash
        System.out.println("=========================Hash=========================");
        jedis.hset("user","userName","wanglu");
        Map<String,String> map = new HashMap<String,String>();
        map.put("password","12345");
        map.put("tel","6789");
        jedis.hset("user",map);
        Map<String, String> user = jedis.hgetAll("user");
        System.out.println(user.toString());
    }
}
