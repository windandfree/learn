    package com.umbrella.learnmq.delayandschedule;

    import org.apache.activemq.ActiveMQConnectionFactory;

    import javax.jms.Connection;
    import javax.jms.JMSException;
    import javax.jms.Message;
    import javax.jms.MessageConsumer;
    import javax.jms.MessageListener;
    import javax.jms.Queue;
    import javax.jms.Session;
    import javax.jms.TextMessage;
    import java.io.IOException;
    import java.util.Date;

    /**
     * @JIRA:
     * @Des:延迟定时消费者  没有特别之处
     * @Author:WL
     * @Date: 16:23 2020/11/1
     */
    public class JmsConsumer_DelayAndSchedule {
        public static final String MY_ACTIVEMQ_URL = "tcp://192.168.1.115:61616";
        public static final String QUEUE_NAME = "queueName1";
        public static void main(String[] args) throws JMSException, IOException {
            ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(MY_ACTIVEMQ_URL);
            Connection connection = activeMQConnectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue(QUEUE_NAME);
            MessageConsumer consumer = session.createConsumer(queue);
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    if(null!=message && message instanceof TextMessage){
                        TextMessage textMessage = (TextMessage) message;
                        try {
                            System.out.println(new Date()+"消费者获取的消息"+textMessage.getText());
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            System.in.read();
            consumer.close();
            session.close();
            connection.close();
        }
    }
