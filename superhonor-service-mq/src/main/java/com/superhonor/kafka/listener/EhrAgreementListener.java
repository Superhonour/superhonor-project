package com.superhonor.kafka.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author liuweidong
 */
@Component
public class EhrAgreementListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(EhrAgreementListener.class);

    @KafkaListener(topics = {"annul"}, groupId = "myContainer1")
    public void annul1(ConsumerRecord<String, String> record) {
        LOGGER.info("annul1 groupId = myContainer2, message = " + record.toString());
    }

    @KafkaListener(topics = {"annul"}, groupId = "myContainer2")
    public void signed2(ConsumerRecord<String, String> record) {
        LOGGER.info("signed2 groupId = myContainer2, message = " + record.toString());
    }

}