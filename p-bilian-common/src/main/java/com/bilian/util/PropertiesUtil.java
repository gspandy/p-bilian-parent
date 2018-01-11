package com.bilian.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author:杨果
 * @date:15/12/1 下午4:16
 * <p/>
 * Description:
 */
public class PropertiesUtil {
    public static Map<String, String> getProperty(String filePath) {
        Map<String, String> map = new HashMap<>();

        try (InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream(filePath)) {
            Properties p = new Properties();
            p.load(in);
            for (Map.Entry entry : p.entrySet()) {
                String key = (String) entry.getKey();
                map.put(key, (String) entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static Map<String, String> getProperty(File file) {
        Map<String, String> map = new HashMap<>();
        try (InputStream in = new FileInputStream(file);) {
            Properties p = new Properties();
            p.load(in);
            for (Map.Entry entry : p.entrySet()) {
                String key = (String) entry.getKey();
                map.put(key, (String) entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
