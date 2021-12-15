package com.qhh.rabbitmq.two;

import com.qhh.rabbitmq.utils.RabbitMqUtil;
import com.rabbitmq.client.Channel;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * 生产者  发送大量消息
 */
public class Task01 {
    public static final String QUEUE_NAME = "hello";

//    发送大量消息
    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtil.getChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//        从控制台中接受信息
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String message = scanner.next();
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println("发送完成：" + message);
        }
    }
}
