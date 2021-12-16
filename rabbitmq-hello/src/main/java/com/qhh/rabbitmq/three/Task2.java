package com.qhh.rabbitmq.three;

import com.qhh.rabbitmq.utils.RabbitMqUtil;
import com.rabbitmq.client.Channel;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * 消息在手动应答时不丢失，放回队列中重新消费
 */
public class Task2 {
    public static final  String taskQueueName = "ackQueue";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtil.getChannel();

        boolean durable = true; //持久化队列。首先要删除原有队列
        channel.queueDeclare(taskQueueName, durable, false, false, null);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String message = scanner.next();
            channel.basicPublish("", taskQueueName, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println("生产者发出消息：" + message);
        }
    }
}
