package com.superhonor.service.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author liuweidong
 */
@RestController
public class HelloWordController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWordController.class);

    @Value("${server.port}")
    private String port;

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping("/hi")
    public String home(@RequestParam(value = "name", defaultValue = "forezp") String name) {
        LOGGER.info("calling trace service-hi  ");
        return restTemplate.getForObject("http://10.10.128.123:8769/service-zero/miya?token=123", String.class);
    }

}
