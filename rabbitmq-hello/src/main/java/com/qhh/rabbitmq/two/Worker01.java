package com.qhh.rabbitmq.two;

import com.qhh.rabbitmq.utils.RabbitMqUtil;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

/**
 * 这是一个工作线程，相当于消费者
 */
public class Worker01 {
    public static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtil.getChannel();

//        消息接收
        DeliverCallback deliverCallback = (consumerTag, message) ->{
            System.out.println("接收到的消息" + new String(message.getBody()));
        };

        CancelCallback cancelCallback = (consumerTag) -> {
            System.out.println(consumerTag+"消费者取消消费接口回调逻辑");
        };

        System.out.println("c2等待接收消息...");
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, cancelCallback);
    }

}
