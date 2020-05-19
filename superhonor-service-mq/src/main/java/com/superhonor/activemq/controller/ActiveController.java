package com.superhonor.activemq.controller;

import com.superhonor.activemq.producer.ActiveProducer;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Destination;

/**
 * @author liuweidong
 */
@RestController
@RequestMapping("/active")
public class ActiveController {

    private ActiveProducer producer;

    public ActiveController(ActiveProducer producer) {
        this.producer = producer;
    }

    @GetMapping("/message")
    public void send() {
        Destination destination = new ActiveMQQueue("mytest.queue");
        for(int i=0; i<10; i++){
            producer.sendMessage(destination, "new message!!!");
        }
    }
}
