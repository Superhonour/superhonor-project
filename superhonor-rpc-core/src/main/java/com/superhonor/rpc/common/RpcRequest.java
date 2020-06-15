package com.superhonor.rpc.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * rpc请求实体类
 * @author liuweidong
 */
@Getter
@Setter
@ToString
public class RpcRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 接口名称 */
    private String interfaceName;

    /** 方法名称 */
    private String methodName;

    /** 参数类型 */
    private Class<?>[] parameterTypes;

    /** 参数 */
    private Object[] parameters;

}
