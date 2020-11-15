package com.umbrella.learnmq.broker;

import org.apache.activemq.broker.BrokerService;

/**
 * @JIRA:HY3-
 * @Des:内嵌式的mq
 * @Author:WL
 * @Date: 19:54 2020/11/5
 */
public class ActiveMq_Broker {
    public static void main(String[] args) throws Exception {
        //ActiveMQ也支持在vm中通信基于嵌入的broker
        BrokerService brokerService = new BrokerService();
        brokerService.setUseJmx(true);
        brokerService.addConnector("tcp://127.0.0.1:61616");
        brokerService.start();
        System.in.read();
    }
}
