package com.umbrella.learnmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @JIRA:HY3-
 * @Des:
 * @Author:WL
 * @Date: 12:36 2020/11/7
 */
@SpringBootApplication
@EnableScheduling //开启定时任务的
public class MqProductApplcation {
    public static void main(String[] args) {
        SpringApplication.run(MqProductApplcation.class,args);
    }
}
