package com.bilian.util;

import java.util.Collection;
import java.util.Map;

/**
 * <p/>
 * <p><b>introduction:</b>
 * </p>
 * <p><b>packageName:</b>com.chinaredstar.utils</p>
 * <p><b>projectName:</b>p-longyi-contract-parent</p>
 * <p><b>User:</b> xuxiaoming <a href="mailto:xu.xiaoming535@chinaredstar.com">xu.xiaoming535@chinaredstar.com</a></p>
 * <p><b>Date:</b>2017/4/11</p>
 *
 * @author xuxiaoming
 */
public class CollectionsUtil {
    public static boolean isEmpty(Collection<?> collection) {
        return (collection == null || collection.isEmpty());
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return (map == null || map.isEmpty());
    }

    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

}
