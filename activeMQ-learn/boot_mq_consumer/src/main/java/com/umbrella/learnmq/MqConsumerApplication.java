package com.umbrella.learnmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @JIRA:HY3-
 * @Des:springboot 整合activemq 的消费者
 * @Author:WL
 * @Date: 14:33 2020/11/7
 */
@SpringBootApplication
public class MqConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MqConsumerApplication.class,args);
    }
}
