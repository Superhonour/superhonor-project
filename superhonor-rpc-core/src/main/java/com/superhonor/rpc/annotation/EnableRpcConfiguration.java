package com.superhonor.rpc.annotation;
import java.lang.annotation.*;

/**
 * 作用于Spring Boot启动类，表示启用RPC功能
 * @author liuweidong
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableRpcConfiguration {

}
