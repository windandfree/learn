package com.umbrella.learnmq.basic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @JIRA:HY3-
 * @Des:消息生产者之Topic，发送测试
 * @Author:WL
 * @Date: 18:17 2020/11/1
 */
public class JmsProduce_topic {
    public static final String MY_ACTIVEMQ_URL = "tcp://192.168.1.115:61616";
    public static final String TOPIC_NAME = "topicName1";
    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(MY_ACTIVEMQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //通过session创建目的地TOPIC
        Topic topic = session.createTopic(TOPIC_NAME);
        MessageProducer producer = session.createProducer(topic);
        for (int i = 0; i < 3; i++) {
            TextMessage textMessage = session.createTextMessage("topic msg===>第" + i + "条");
            producer.send(textMessage);
        }
        producer.close();
        session.close();
        connection.close();
        System.out.println("activemq topic 发送消息完毕");

        TextMessage textMessage = session.createTextMessage("topic msg===>第" + 1 + "条");
//        textMessage.setproperty
    }
}
