package com.utils.propertiesutils;

import java.io.IOException;
import java.util.Properties;

/**
 * @ProjectName: springboot-utils
 * @Package: com.utils.propertiesutils
 * @ClassName: PropertyUtils
 * @Author: zhangqiang
 * @Description: 读取配置文件的工具类/静态读取
 * @Date: 2019/9/30 12:01 上午
 * @Version: 1.0
 */
public class PropertyUtilsByPro {
    private static Properties properties;
    static {
        if(null==properties){
            properties=new Properties();
            try {
                properties.load(PropertyUtilsByPro.class.getResourceAsStream("/config.properties"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static String getProperty(String key){
        return properties.getProperty(key);
    }

}
