package com.superhonor.service.zuul.filter;

import com.superhonor.service.zuul.configuration.GateWaysCrosConfig;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Test {

    private static final Logger LOGGER = LoggerFactory.getLogger(GateWaysCrosConfig.class);

    public static void main(String[] args) {
        List <Object> list = new ArrayList <>();
        LOGGER.error("123");
        LOGGER.info("123");
        LOGGER.warn("123");
        list.add("1");
        list.add(1);
        if(CollectionUtils.isNotEmpty(list)) {
            list.forEach(e -> System.out.println(e));
        }
    }
}
