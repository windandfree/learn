package com.umbrella.learnmq.spring;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * @JIRA:HY3-
 * @Des:spring整合activemq
 * @Author:WL
 * @Date: 19:56 2020/11/6
 */
@Service
public class SpringMQ_Product {
    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        SpringMQ_Product springMQ_product = (SpringMQ_Product) ctx.getBean("springMQ_Product");
        /*springMQ_product.jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage("spring和activemq的整合！");
                return textMessage;
            }
        });*/
        springMQ_product.jmsTemplate.send(session -> session.createTextMessage("spring和activemq的整合！--监听器"));

        System.out.println("************send task over**********");
    }
}
