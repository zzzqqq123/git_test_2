package com.utils.propertiesutils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: springboot-utils
 * @Package: com.utils.propertiesutils
 * @ClassName: PropertyUtilsByAnno
 * @Author: zhangqiang
 * @Description: 根据注解读取配置文件,根据前缀匹配数据
 * @Date: 2019/9/30 12:27 上午
 * @Version: 1.0
 */
@Component
@PropertySource(value = "classpath:config.properties")
@ConfigurationProperties(prefix = "springbootutols")
public class PropertyUtilsByAnno {
    private String prifix;

    public String getPrifix() {
        return prifix;
    }

    public void setPrifix(String prifix) {
        this.prifix = prifix;
    }
}
