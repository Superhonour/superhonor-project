package com.superhonor.rpc.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * rpc响应实体类
 * @author liuweidong
 */
@Getter
@Setter
@ToString
public class RpcResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 错误 */
    private Throwable error;

    /** 结果 */
    private Object result;

}
