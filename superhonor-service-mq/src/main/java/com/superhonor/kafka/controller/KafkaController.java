package com.superhonor.kafka.controller;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuweidong
 */
@RestController
@RequestMapping("/kafka")
public class KafkaController {

    private KafkaTemplate<String, String> kafkaTemplate;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public KafkaController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("/sendTest")
    public String sendTest(String msg) {
        kafkaTemplate.send("annul", "message", msg);
        return "send:" + msg + "finish";
    }
}
