package com.superhonor.activemq.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author liuweidong
 */
@Component
public class ActiveConsumer {

    @JmsListener(destination = "mytest.queue")
    public void receiveQueue(String text) {
        System.out.println("Consumer receive:"+text);
    }

}
