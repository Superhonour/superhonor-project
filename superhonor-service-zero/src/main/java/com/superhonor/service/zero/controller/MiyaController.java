package com.superhonor.service.zero.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuweidong
 */
@RestController
public class MiyaController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MiyaController.class);

    @RequestMapping("/miya")
    public String miya() throws InterruptedException {
        LOGGER.info("info is being called");
        Thread.sleep(60000);
        LOGGER.error("info is being called");
        return "miya";
    }
}
