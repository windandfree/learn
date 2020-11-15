package com.umbrella.learnmq.mqproduct;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @JIRA:HY3-
 * @Des:消息生产者
 * @Author:WL
 * @Date: 12:53 2020/11/7
 */
@Component
public class Queue_Product {
    
    private int i = 0;

    //JmsMessagingTemplate是Springboot的Jms模板,Spring的是JmsTemplate
    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;
    
    @Resource  //把ConfigBean类的ActiveMQQueue注入进来
    private ActiveMQQueue activeMQQueue;

    public void sendMessage(String msg){
        //创建了消息发送者、消息 并指明了目的地
        jmsMessagingTemplate.convertAndSend(activeMQQueue,"springboot 整合 activemq 发送的消息:"+msg);
    }

    @Scheduled(fixedDelay = 3000)
    public void sendMessageTask(){
//        this.sendMessage("定时任务：第"+i+"次发送");
        this.sendTopicMessage("定时任务主题：第"+i+"次发送");//主题发送消息
        System.out.println("定时任务：第"+i+"次发送");
        i++;
    }

    @Resource  //把ConfigBean类的ActiveMQTopic注入进来
    private ActiveMQTopic activeMQTopic;
    public void sendTopicMessage(String msg){
        jmsMessagingTemplate.convertAndSend(activeMQTopic,"springboot 整合 activemq 发送的消息:"+msg);
    }
}
