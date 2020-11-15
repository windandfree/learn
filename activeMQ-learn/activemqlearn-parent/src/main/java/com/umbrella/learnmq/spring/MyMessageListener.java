package com.umbrella.learnmq.spring;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @JIRA:HY3-
 * @Des:
 * @Author:WL
 * @Date: 21:03 2020/11/6
 */
@Component
public class MyMessageListener implements MessageListener {
    @SneakyThrows
    @Override
    public void onMessage(Message message) {
        if (null!=message && message instanceof TextMessage){
            TextMessage textMessage = (TextMessage) message;
            System.out.println("监听器 消费者 接收到的消息："+textMessage.getText());
        }
    }
}
