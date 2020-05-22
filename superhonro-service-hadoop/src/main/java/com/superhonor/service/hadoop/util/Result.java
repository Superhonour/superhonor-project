package com.superhonor.service.hadoop.util;

import lombok.Data;

/**
 * @author liuweidong
 */
@Data
public class Result {
    private String resCode;
    private String resDes;
    private Object data;
    public static final String FAILURE = "sys-00-01";
    public static final String SUCCESS = "SUCCESS";

    public Result(String resCode, String resDes, Object data) {
        this.resCode = resCode;
        this.resDes = resDes;
        this.data = data;
    }

    public Result(String resCode, String resDes) {
        this.resCode = resCode;
        this.resDes = resDes;
    }
}
