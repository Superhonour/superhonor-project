package com.superhonor.rpc.util;

/**
 * RPC异常
 *
 * @author liuweidong
 */
public class RpcException extends RuntimeException {

    private static final long serialVersionUID = -7465652719266901598L;

    public RpcException() {
        super();
    }

    public RpcException(String message) {
        super(message);
    }

    public RpcException(String message, Throwable cause) {
        super(message, cause);
    }

    public RpcException(Throwable cause) {
        super(cause);
    }

    public RpcException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
