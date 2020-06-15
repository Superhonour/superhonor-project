package com.superhonor.rpc.conf;

import com.superhonor.rpc.util.IpUtils;

/**
 * 常量
 * @author liuweidong
 */
public interface Consts {

    /** zk服务根节点 */
    String ZK_SERVICE_ROOT = "/ZrpcServeres";

    /** 本机IP */
    String LOCAL_IP = IpUtils.getLocalAddress();
}
