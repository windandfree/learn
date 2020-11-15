package com.umbrella.learnmq.asyn;

        import org.apache.activemq.ActiveMQConnection;
        import org.apache.activemq.ActiveMQConnectionFactory;
        import org.apache.activemq.ActiveMQMessageProducer;
        import org.apache.activemq.AsyncCallback;

        import javax.jms.Connection;
        import javax.jms.JMSException;
        import javax.jms.MessageProducer;
        import javax.jms.Queue;
        import javax.jms.Session;
        import javax.jms.TextMessage;
        import java.util.UUID;

/**
 * @JIRA:HY3-
 * @Des:异步投递回调确认
 * @Author:WL
 * @Date: 11:14 2020/11/15
 */
public class AysnDeliveryProduce {
    private static final String MY_ACTIVEMQ_URL = "tcp://192.168.1.115:61616";//这里也可以开启异步投递
    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(MY_ACTIVEMQ_URL);
//        activeMQConnectionFactory.setUseAsyncSend(true);//开启异步投递
        ActiveMQConnection connection = (ActiveMQConnection) activeMQConnectionFactory.createConnection();
        connection.setUseAsyncSend(true);//开启异步投递
        connection.start();
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Queue queueName = session.createQueue("ASYN_DELIVERY_QUEUE_NAME");
        ActiveMQMessageProducer activeMQMessageProducer = (ActiveMQMessageProducer) session.createProducer(queueName);
        for (int i = 0; i < 3; i++) {
            TextMessage textMessage = session.createTextMessage("ASYN_DELIVERY_TEST MSG   " + i);
            textMessage.setJMSMessageID(UUID.randomUUID().toString()+"order_asyn_test");
            String messageId= textMessage.getJMSMessageID();
            activeMQMessageProducer.send(textMessage, new AsyncCallback() {
                //每一条消息发送都调用一次回执，或成功回执 或失败回执
                @Override
                public void onSuccess() {//发送成功的回执方法
                    System.out.println(messageId + "该条消息已经成功发送");
                }

                @Override
                public void onException(JMSException exception) {//发送失败的回执方法
                    System.out.println(messageId + "该条消息已经发送失败");
                }
            });
        }
        activeMQMessageProducer.close();
        session.close();
        connection.close();

    }
}
