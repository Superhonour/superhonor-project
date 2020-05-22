package com.superhonor.service.hadoop.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * HDFS配置类
 * @author linhaiy
 * @date 2019.05.18
 */
@Configuration
@Setter
@Getter
public class HdfsConfig {

    @Value("${hdfs.path}")
    private String path;
}
