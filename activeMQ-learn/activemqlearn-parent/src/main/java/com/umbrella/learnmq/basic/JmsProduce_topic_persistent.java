package com.umbrella.learnmq.basic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @JIRA:HY3-
 * @Des:消息生产者之持久化的Topic，发送测试
 * @Author:WL
 * @Date: 18:17 2020/11/1
 */
public class JmsProduce_topic_persistent {
    public static final String MY_ACTIVEMQ_URL = "tcp://192.168.1.115:61616";
    public static final String TOPIC_NAME = "topicName1";
    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(MY_ACTIVEMQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //通过session创建目的地TOPIC
        Topic topic = session.createTopic(TOPIC_NAME);
        MessageProducer producer = session.createProducer(topic);
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);//设置持久化生产者***
        connection.start();//设置完了才启动***
        for (int i = 0; i < 3; i++) {
            TextMessage textMessage = session.createTextMessage("持久化topic发送的消息===>第" + i + "条");
            producer.send(textMessage);
        }
        producer.close();
        session.close();
        connection.close();
        System.out.println("activemq topic 发送消息完毕");

    }
}
