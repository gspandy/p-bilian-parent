package com.bilian.exception;

/**
 * <p/>
 * <p><b>introduction:</b>
 * </p>
 * <p><b>packageName:</b>com.bilian.exception</p>
 * <p><b>projectName:</b>p-bilian-parent</p>
 * <p><b>User:</b> xuxiaoming <a href="mailto:xu.xiaoming535@chinaredstar.com">xu.xiaoming535@chinaredstar.com</a></p>
 * <p><b>Date:</b>2018/1/12</p>
 *
 * @author xuxiaoming
 */
public class BilianException extends  RuntimeException {

    public BilianException() {
    }

    public BilianException(String message) {
        super(message);
    }

    public BilianException(String message, Throwable cause) {
        super(message,cause);
    }


    public BilianException(Throwable cause) {
        super(cause);
    }

    public BilianException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message,cause,enableSuppression,writableStackTrace);
    }


}
