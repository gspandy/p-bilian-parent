package com.bilian.dubbo.service;

import com.bilian.bilianInterface.BilianDeleteRedisInterface;
import com.bilian.bilianInterface.BilianInsertRedisInterface;
import com.bilian.enums.BilianRedisEnums;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * <p/>
 * <p><b>introduction:redis数据处理类处理类</b>
 * </p>
 * <p><b>packageName:</b>com.chinaredstar.dubbo.service</p>
 * <p><b>projectName:</b>p-longyi-contract-parent</p>
 * <p><b>User:</b> xuxiaoming <a href="mailto:xu.xiaoming535@chinaredstar.com">xu.xiaoming535@chinaredstar.com</a></p>
 * <p><b>Date:</b>2018/1/18</p>
 *
 * @author xuxiaoming
 */
@Service
public class RedisCompentService {
    private static Logger logger = LoggerFactory.getLogger(RedisCompentService.class);


    @BilianInsertRedisInterface(value = "#id",bilianRedisEnums= BilianRedisEnums.BILIAN_DEFULT)
    public Object getObjectDemo(Long id) {
        return null;

    }

    @BilianDeleteRedisInterface(value = "#object.getId()",bilianRedisEnums= BilianRedisEnums.BILIAN_DEFULT)
    public void updateDataDemo(Object object) {


    }

}
