package com.umbrella.learnmq.basic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @JIRA:HY3-
 * @Des:持久化topic消费者小案例之异步非阻塞方式
 * @Author:WL
 * @Date: 18:18 2020/11/1
 */
public class JmsConsumer_topic_persistent {
    public static final String MY_ACTIVEMQ_URL = "tcp://192.168.1.115:61616";
    public static final String TOPIC_NAME = "topicName1";
    public static void main(String[] args) throws JMSException, IOException {
        System.out.println("持久化topic 消费者1号");
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(MY_ACTIVEMQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.setClientID("persistent topic 1");//设置每个订阅者的clientID
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //4、创建目的地Topic
        Topic topic = session.createTopic(TOPIC_NAME);
        TopicSubscriber topicSubscriber = session.createDurableSubscriber(topic, "这是一个持久化的订阅者");
        connection.start();
        
        Message message = topicSubscriber.receive();
        
        while (message != null){
            TextMessage textMessage = (TextMessage) message;
            System.out.println("持久化topic接收的消息：   "+textMessage.getText());
            message = topicSubscriber.receive();
        }
        session.close();
        connection.close();
        
        /*1一定要先运行一次消费者，等于向MQ注册（类似微信订阅了这个主题）
          2然后再运行生产者发送信息，此时
          3无论消费者是否在线，都会接收到，不在线的话（类似关闭微信，再打开），下次连接的时候，会把没有收过的消息都接收下来。*/

    }
}
