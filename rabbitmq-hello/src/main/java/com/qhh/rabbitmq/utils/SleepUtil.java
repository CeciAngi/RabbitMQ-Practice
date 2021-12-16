package com.qhh.rabbitmq.utils;

/**
 * 睡眠工具类
 */
public class SleepUtil {
    public static void sleep(int second){
        try {
            Thread.sleep(1000 * second);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
