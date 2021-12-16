package com.qhh.rabbitmq.three;

import com.qhh.rabbitmq.utils.RabbitMqUtil;
import com.qhh.rabbitmq.utils.SleepUtil;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;


/**
 * 消息在手动应答时不丢失，放回队列中重新消费
 */
public class Worker03 {
    public static final  String taskQueueName = "ackQueue";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtil.getChannel();
        System.out.println("C1等到接收消息处理时间短");

//        手动应答
        Boolean autoAck = false;
        DeliverCallback deliverCallback = (consumerTag, message) ->{
//            沉睡1s
            SleepUtil.sleep(1);
            System.out.println("接收到的消息" + new String(message.getBody()));
            /**
             * 手动应答
             * 1、消息的标记 tag
             * 2、是否批量应答
             */
            channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
        };

        CancelCallback cancelCallback = (consumerTag) -> {
            System.out.println(consumerTag+"消费者取消消费接口回调逻辑");
        };
        channel.basicConsume(taskQueueName, autoAck, deliverCallback, cancelCallback);
    }
}
