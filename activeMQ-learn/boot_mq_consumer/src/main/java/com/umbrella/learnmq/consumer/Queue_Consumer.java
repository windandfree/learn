package com.umbrella.learnmq.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * @JIRA:HY3-
 * @Des:
 * @Author:WL
 * @Date: 14:34 2020/11/7
 */
@Component
public class Queue_Consumer {
    
    @JmsListener(destination = "${myQueueName}")
    public void consumerMsg(TextMessage textMessage) throws JMSException {
        String msg = textMessage.getText();
        System.out.println("消费者收到的msg："+msg);
    }
}
