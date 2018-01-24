package com.bilian.monitor;

import com.alibaba.dubbo.common.utils.NetUtils;
import com.bilian.util.PropertiesUtil;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.Properties;

/**
 * Created by IntelliJ IDEA
 * User:徐晓鸣
 * Date:15/8/20
 * Time:下午4:07
 * <p/>
 * Description:
 * <p/>
 * 加载Graphite的配置文件
 */
public class GraphitePropertyPlaceholderConfigurer  extends PropertyPlaceholderConfigurer {
    public static final String ip = NetUtils.getLocalHost();
    public static final String appName = PropertiesUtil.getProperty("disconf.properties").get("disconf.app");
    public static final String metricsPrefix = appName + "." + ip.replaceAll("\\.", "_");


    public void setProperties() {
        Properties properties = new Properties();
        properties.put("metricsPrefix", metricsPrefix);
        setProperties(properties);
    }
}

