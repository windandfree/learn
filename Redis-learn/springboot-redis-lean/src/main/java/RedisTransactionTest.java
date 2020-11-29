import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;

/**
 * @Des:Redis事务测试
 * @Author:WL
 * @Date: 14:41 2020/11/28
 */
public class RedisTransactionTest {
    public static void main(String[] args) {
        final Jedis jedis = new Jedis("192.168.1.115",6379);
        final int decryNum = 10;
        jedis.watch("balance");
        if(Integer.parseInt(jedis.get("balance"))>decryNum){
            Transaction multi = jedis.multi();
            multi.decrBy("balance",decryNum);
            multi.incrBy("derby",decryNum);
            List<Object> exec = multi.exec();
            if(exec== null || exec.size() < 1){//没有返回 说明balance被其他客户端修改过。所有操作不提交。
                System.out.println("支付失败");
            }else System.out.println("支付成功");
        }else{
            jedis.unwatch();
            System.out.println("余额不足");
        }
    }
}
