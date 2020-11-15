package com.umbrella.learnmq.spring;

        import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.context.ApplicationContext;
        import org.springframework.jms.core.JmsTemplate;
        import org.springframework.stereotype.Service;

/**
 * @JIRA:HY3-
 * @Des:spring整合activemq
 * @Author:WL
 * @Date: 20:13 2020/11/6
 */
@Service
public class SpringMQ_Consumer {
    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        SpringMQ_Consumer springMQ_consumer = (SpringMQ_Consumer) ctx.getBean("springMQ_Consumer");
        String receiveValue = (String) springMQ_consumer.jmsTemplate.receiveAndConvert();
        System.out.println("****spring整合activemq，消费者收到的消息:"+receiveValue);
    }
}
