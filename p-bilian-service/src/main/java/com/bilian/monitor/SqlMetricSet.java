package com.bilian.monitor;

import com.codahale.metrics.Gauge;
import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricSet;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeData;
import java.lang.management.ManagementFactory;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.codahale.metrics.MetricRegistry.name;

/**
 * jdbc慢查询sql数量上报metric. Created by li.rui on 2016/4/26.
 */
public class SqlMetricSet implements MetricSet {
    private static final String slowQueryReport = "tomcat.jdbc:type=org.apache.tomcat.jdbc.pool.interceptor.SlowQueryReportJmx,name=jdbcPool";
    private final ObjectName objectName;
    private final MBeanServer mBeanServer;
    private static final String attributeName = "SlowQueriesCD";

    public SqlMetricSet() throws Exception {
        this(slowQueryReport, ManagementFactory.getPlatformMBeanServer());
    }

    public SqlMetricSet(String slowQueryReport, final MBeanServer mBeanServer) throws Exception {
        this.objectName = new ObjectName(slowQueryReport);
        this.mBeanServer = mBeanServer;
    }

    /**
     * A map of metric names to metrics.
     *
     * @return the metrics
     */
    @Override
    public Map<String, Metric> getMetrics() {
        final Map<String, Metric> map = new HashMap<>();
        //异常SQL数
        map.put(name("exceptionSql", "typeCount"), new Gauge<Integer>() {
            @Override
            public Integer getValue() {
                CompositeData[] cs = null;
                if (mBeanServer.isRegistered(objectName)) {
                    try {
                        cs = (CompositeData[]) mBeanServer.getAttribute(objectName, attributeName);
                    } catch (Exception ignored) {
                    }
                }
                int count = 0;
                if (cs != null) {
                    for (CompositeData c : cs) {
                        count++;
                    }
                }
                return count;
            }
        });
        //异常SQL执行失败之和
        map.put(name("exceptionSql", "failuresCount"), new Gauge<Long>() {
            @Override
            public Long getValue() {
                CompositeData[] cs = null;
                if (mBeanServer.isRegistered(objectName)) {
                    try {
                        cs = (CompositeData[]) mBeanServer.getAttribute(objectName, attributeName);
                    } catch (Exception ignored) {
                    }
                }
                Long count = 0L;
                if (cs != null) {
                    for (CompositeData c : cs) {
                        count += (Long) c.get("failures");
                    }
                }
                return count;
            }
        });
        return Collections.unmodifiableMap(map);
    }
}
