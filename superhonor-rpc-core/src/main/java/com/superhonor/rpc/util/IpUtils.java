package com.superhonor.rpc.util;

import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * IP相关工具类
 *
 * @author liuweidong
 */
@Slf4j
public class IpUtils {

    /**
     * 获取本机IP
     * @return
     */
    public static String getLocalAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.error("获取本机IP异常", e);
        }
        return "127.0.0.1";
    }
}
