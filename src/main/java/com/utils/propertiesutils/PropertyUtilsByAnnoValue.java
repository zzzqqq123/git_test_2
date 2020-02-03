package com.utils.propertiesutils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: springboot-utils
 * @Package: com.utils.propertiesutils
 * @ClassName: PropertyUtilsByAnnoValue
 * @Author: zhangqiang
 * @Description: 根据@Value获取数据
 * @Date: 2019/9/30 12:41 上午
 * @Version: 1.0
 */
@Component
@PropertySource("classpath:config.properties")
public class PropertyUtilsByAnnoValue {
    @Value("${springbootutols.prifix}")
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
