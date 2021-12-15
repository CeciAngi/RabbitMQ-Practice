package com.qhh.rabbitmq.one;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者：接收消息
 */
public class Consumer {
    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
//        创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.255.128");
        factory.setUsername("admin");
        factory.setPassword("123");
//        创建连接
        Connection connection = factory.newConnection();
//        获取信道
        Channel channel = connection.createChannel();

        /**
         * 消费者接收消息
         * 1、消费哪个队列
         * 2、消费之后是否自应当
         * 3、消费者未成功消费的回调
         * 4、消费者取消消费的回调
         */

        DeliverCallback deliverCallback = (consumerTag, message) ->{
            System.out.println(new String(message.getBody()));
        };

        CancelCallback cancelCallback = (consumerTag) -> {
            System.out.println("消费消息被中断");
        };

        channel.basicConsume(QUEUE_NAME, true, deliverCallback, cancelCallback);

    }

}
