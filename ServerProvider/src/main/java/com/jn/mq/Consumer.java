package com.jn.mq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消息消费者
 */
public class Consumer {

    public static void main(String[] args) throws Exception {

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                "tcp://127.0.0.1:61616");

        Connection connection = connectionFactory.createConnection();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        connection.start();

        Queue queue = session.createQueue("user");

        MessageConsumer consumer = session.createConsumer(queue);

        while (true) {
            TextMessage receive = (TextMessage) consumer.receive();
            System.out.println("消费消息："+receive.getText());
        }

    }
}
