package com.superhonor.service.zero.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liuweidong
 */
@Data
public class Student implements Serializable {

    private Long id;
    private String name;
    private Integer age;
}
