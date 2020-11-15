package com.umbrella.learnmq.basic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @JIRA:HY3-
 * @Des:topic消费者小案例之异步非阻塞方式
 * @Author:WL
 * @Date: 18:18 2020/11/1
 */
public class JmsConsumer_topic {
    public static final String MY_ACTIVEMQ_URL = "tcp://192.168.1.115:61616";
    public static final String TOPIC_NAME = "topicName1";
    public static void main(String[] args) throws JMSException, IOException {
        System.out.println("topic 消费者1号");
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(MY_ACTIVEMQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //4、创建目的地Topic
        Topic topic = session.createTopic(TOPIC_NAME);
        MessageConsumer consumer = session.createConsumer(topic);
        //通过监听的方式来消费消息
        consumer.setMessageListener(message -> {
            if(null!=message && message instanceof TextMessage){
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("消费者订阅主题的消息"+textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        System.in.read();
        consumer.close();
        session.close();
        connection.close();
    }
}
