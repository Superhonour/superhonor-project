package com.superhonor.service.zero.controller;

import com.superhonor.service.zero.dto.Student;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

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

    @GetMapping("test")
    @Cacheable(cacheNames = {"student"})
    public Student getById(@RequestParam("id") Long id) {
        ConcurrentHashMap<String, Object> concurrentHashMap = new ConcurrentHashMap<>(10, 0, 1);
        concurrentHashMap.put("date", new Date());
        System.out.println(concurrentHashMap.toString());
        LOGGER.info("查询" + id + "编号的学生");
        Student student = new Student();
        student.setId(id);
        student.setName("张三" + id);
        student.setAge(18);
        return student;
    }

}
