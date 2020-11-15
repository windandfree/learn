package com.umbrella.learnmq.delayandschedule;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ScheduledMessage;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.util.Date;

/**
 * @JIRA:HY3-
 * @Des:延迟定时发送测试
 * @Author:WL
 * @Date: 15:12 2020/11/1
 */
public class JmsProduce_DelayAndSchedule {
    public static final String MY_ACTIVEMQ_URL = "tcp://192.168.1.115:61616";
    public static final String QUEUE_NAME = "queueName1";
    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(MY_ACTIVEMQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        MessageProducer producer = session.createProducer(queue);
        long delay = 30 * 1000;
        long period = 10 * 1000;
        int repeat = 5;
        for (int i = 0; i < 3; i++) {
            TextMessage textMessage = session.createTextMessage("delay msg===>第" + i + "条");
            textMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY,delay);
            textMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_PERIOD,period);
            textMessage.setIntProperty(ScheduledMessage.AMQ_SCHEDULED_REPEAT,repeat);
            System.out.println(new Date() + "        发送者"+  i + "条");
            producer.send(textMessage);
        }

        producer.close();
        session.close();
        connection.close();
        System.out.println("activemq 发送消息完毕");
    }
}
