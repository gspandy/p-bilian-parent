package com.bilian.enums;

import java.util.concurrent.TimeUnit;

/**
 * <p/>
 * <p><b>introduction:</b>
 * </p>
 * <p><b>packageName:</b>com.chinaredstar.dubbo.api.enums</p>
 * <p><b>projectName:</b>p-longyi-contract-parent</p>
 * <p><b>User:</b> xuxiaoming <a href="mailto:xu.xiaoming535@chinaredstar.com">xu.xiaoming535@chinaredstar.com</a></p>
 * <p><b>Date:</b>2018/1/16</p>
 *
 * @author xuxiaoming
 */
public enum BilianRedisEnums {
    BILIAN_DEFULT("bilian:defult:id:", 0,null, "默认缓存"),

    BILIAN_USER("bilian:user:id:", 0,null, "用户id");



    /**
     * 缓存Key的前缀
     */
    private String keyPrefix;

    /**
     * 过期时间
     */
    private long timeout;

    /**
     * 过期时间单位
     */
    private TimeUnit timeUnit;

    /**
     * 描述
     */
    private String desc;

    BilianRedisEnums(String keyPrefix, long timeout, TimeUnit timeUnit, String desc){
        this.keyPrefix = keyPrefix;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.desc = desc;
    }

    public long getTimeout() {
        return timeout;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * 获取完成的Key
     * @param key
     * @return
     */
    public String getKey(String key){
        return keyPrefix + key;
    }
}
