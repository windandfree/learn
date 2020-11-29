import redis.clients.jedis.Jedis;

/**
 * @JIRA:HY3-
 * @Des:redis主从复制测试
 * @Author:WL
 * @Date: 14:47 2020/11/28
 */
public class RedisMStest {
    public static void main(String[] args) {
        Jedis jedisMaster = new Jedis("",6379);//主库
        Jedis jedisSlave = new Jedis("",6380);//从库
        jedisMaster.set("k1","v1");
        String k1 = jedisSlave.get("k1");//注意这里第一次可能取不到值，
        // 因为全量同步需要将磁盘上的数据同步到从库，磁盘的速度跟不上这里内存里的执行速度，第二次只会同步指令，可以取到值，
        //实际应用中不会在同一处进行这样的操作，所以不会出现这类情况
        System.out.println(k1);
    }
}
