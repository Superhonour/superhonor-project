package com.superhonor.activemq.producer;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;

/**
 * @author liuweidong
 */
@Component
public class ActiveProducer {

    /**
     * 也可以注入JmsTemplate，JmsMessagingTemplate对JmsTemplate进行了封装
     */
    private JmsTemplate jmsTemplate;

    public ActiveProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    /**
     * 发送消息，destination是发送到的队列，message是待发送的消息
     * @param destination 发送到的队列
     * @param message 待发送的消息
     */
    public void sendMessage(Destination destination, final String message){
        jmsTemplate.convertAndSend(destination, message);
    }
}
