package com.jn.mq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消息生产者
 */
public class Producter {

    public static void main(String[] args) throws Exception {

        //连接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                "tcp://127.0.0.1:61616");
        //获取连接
        Connection connection = connectionFactory.createConnection();
        //获取Session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建Queue
        Queue queue = session.createQueue("user");
        //消息创建者
        MessageProducer producer = session.createProducer(queue);
        //生产消息
        for (int i = 0; i < 100; i++) {
            TextMessage textMessage = session.createTextMessage("Hi:---->");
            producer.send(textMessage);
            Thread.sleep(1000);
        }
        //关闭连接
        connection.close();
    }

}
