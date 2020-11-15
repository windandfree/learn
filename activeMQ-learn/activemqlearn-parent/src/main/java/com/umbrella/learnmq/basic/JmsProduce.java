package com.umbrella.learnmq.basic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @JIRA:HY3-
 * @Des:active的消息生产者之queue，发送测试
 * @Author:WL
 * @Date: 15:12 2020/11/1
 */
public class JmsProduce {
//    public static final String MY_ACTIVEMQ_URL = "tcp://192.168.1.115:61616";
    public static final String MY_ACTIVEMQ_URL = "nio://192.168.1.115:61618";//使用NIO tcp
//    public static final String MY_ACTIVEMQ_URL = "tcp://127.0.0.1:61616";//测试内嵌broker
    public static final String QUEUE_NAME = "queueName1";
    public static void main(String[] args) throws JMSException {
        //1、创建连接工厂，按照给定的url，采用默认的用户名密码
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(MY_ACTIVEMQ_URL);
        //2、连接工厂创建连接,并启动访问
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        
        //3、通过connection来创建session  注意这里有两个参数：开启事务，签收参数，具体用法看后面
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //4、通过session创建目的地，注意这里可以是destination 类似于下面的代码，
        // queue和destination的关系就像Collection和List的关系
        //Destination destination = session.createQueue(QUEUE_NAME);
        Queue queue = session.createQueue(QUEUE_NAME);
        
        //5、通过session创建消息生产者，并需要指明目的地
        MessageProducer producer = session.createProducer(queue);
//        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);//设置非持久化，queue默认是持久化的
        for (int i = 0; i < 3; i++) {
            //6、创建3条最简单的字符串消息
            TextMessage textMessage = session.createTextMessage("msg===>第" + i + "条");
            //7、通过消息生产者发送消息到具体的目的地，这里是队列
            producer.send(textMessage);
        }

        //8、最后释放资源
        producer.close();
        session.close();
        connection.close();
        
        System.out.println("activemq 发送消息完毕");
    }
}
