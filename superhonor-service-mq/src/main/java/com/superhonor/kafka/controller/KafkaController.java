package com.superhonor.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author liuweidong
 */
@Controller
@RequestMapping("/kafka")
public class KafkaController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/sendTest")
    public String sendTest(String msg) {
//        Map<String, String> message = new HashMap<>(1);
//        message.put("message", msg);
        kafkaTemplate.send("annul", "message", msg);
        return "send:" + msg + "finish";
    }
}
