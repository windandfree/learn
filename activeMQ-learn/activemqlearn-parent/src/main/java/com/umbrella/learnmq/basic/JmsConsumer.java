    package com.umbrella.learnmq.basic;
    
    import org.apache.activemq.ActiveMQConnectionFactory;
    
    import javax.jms.*;
    import java.io.IOException;
    
    /**
     * @JIRA:
     * @Des:activemq 消费者小案例
     * @Author:WL
     * @Date: 16:23 2020/11/1
     */
    public class JmsConsumer {
        public static final String MY_ACTIVEMQ_URL = "tcp://192.168.1.115:61616";
//        public static final String MY_ACTIVEMQ_URL = "nio://192.168.1.115:61618";//使用NIO tcp
//        public static final String MY_ACTIVEMQ_URL = "tcp://127.0.0.1:61616";//测试内嵌broker
        public static final String QUEUE_NAME = "queueJdbcName1";
        public static void main(String[] args) throws JMSException, IOException {
            //1、创建连接工厂，按照给定的url，采用默认的用户名密码
            ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(MY_ACTIVEMQ_URL);
            //2、连接工厂创建连接,并启动访问
            Connection connection = activeMQConnectionFactory.createConnection();
            connection.start();
    
            //3、创建session  注意这里有两个参数：开启事务，签收参数，具体用法看后面
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    
            //4、创建目的地，注意这里可以是destination 类似于下面的代码，
            // queue和destination的关系就像Collection和List的关系
            //Destination destination = session.createQueue(QUEUE_NAME);
            Queue queue = session.createQueue(QUEUE_NAME);
            
            //5、创建消费者
            MessageConsumer consumer = session.createConsumer(queue);
           /* //同步阻塞方式t(receive())
            //订阅者或接收者调HNessageConsumerhireceive()方法来接收消息，receive方法在能够接收到消息之前（或超时之前）将一直阻塞
            while (true){
                //6、消费者获取消息
                //设置没有接受到消息的等待时间，为空无限等待
                TextMessage textMessage = (TextMessage)consumer.receive(3000);
                if(textMessage!=null){
                    System.out.println("消费者获取的消息"+textMessage.getText());
                }else break;
            }*/
           
            //通过监听的方式来消费消息
    //        异步非阻塞方式(监听器onMessage())
    //        订阅者或接收者通过MessageConsumer.setMessageListener(MessageListener listener)注册一个消息监听器，
    //        当消息到达之后，系统自动调用监听器MessageListener的onMessage(Message message)方法。
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    if(null!=message && message instanceof TextMessage){
                        TextMessage textMessage = (TextMessage) message;
                        try {
                            System.out.println("消费者获取的消息"+textMessage.getText());
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            System.in.read();//保障当前程序不被关闭，这句代码的实际意思是等待控制台输入，有了输入才进行后面操作
            //因为连接mq需要时间，没有连接到，就直接关闭了资源显然是获取不到消息的。
            //7、最后释放资源
            consumer.close();
            session.close();
            connection.close();

            /** 多个消费者情况
             * 先生产消息，先启动1号消费者再启动2号消费者，问题:2号消费者还能消费消息吗? 
             * 答：不能，2号没有消息可以消费了
             * 先启动2个消费者，再生产6条消息，消问，消费情况如何?
             * 答：各自一半，有点类似于ribbon中的轮询，
             */

        }
    }
