package com.superhonor.web.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author liuweidong
 */
@RestController
public class HelloWordController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWordController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${server.port}")
    String port;

    @RequestMapping("/hi")
    public String home(@RequestParam(value = "name", defaultValue = "forezp") String name) {
        LOGGER.info("calling trace service-hi  ");
        return restTemplate.getForObject("http://10.10.128.123:8769/service-zero/miya?token=123", String.class);
    }

}
