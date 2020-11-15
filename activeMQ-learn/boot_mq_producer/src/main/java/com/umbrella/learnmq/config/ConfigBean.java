package com.umbrella.learnmq.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Component;

/**
 * @JIRA:HY3-
 * @Des: mq目的地配置文件
 * @Author:WL
 * @Date: 12:46 2020/11/7
 */
@Component
@EnableJms //开启JMS适配注解
public class ConfigBean {
    @Value("${myQueueName}")
    private String myQueueName;
    
    //配置目的地 并使用自定义name
    @Bean //等于在spring的配置文件中的<bean>标签
    public ActiveMQQueue queue(){
        return new ActiveMQQueue(myQueueName);
    }
    
    
    @Bean //发送主题需要的配置
    public ActiveMQTopic topic(){
        return new ActiveMQTopic(myQueueName);
    }
}
