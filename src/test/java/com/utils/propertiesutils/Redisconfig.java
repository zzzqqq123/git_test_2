package com.utils.propertiesutils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * @ProjectName: springboot-utils
 * @Package: com.utils.propertiesutils
 * @ClassName: Redisconfig
 * @Author: zhangqiang
 * @Description: redis配置测试
 * @Date: 2019/11/29 3:35 下午
 * @Version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Redisconfig {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test() throws IOException {
        List<String> list = new ArrayList<>();
        System.out.println("开始时间：" + System.currentTimeMillis());
        redisTemplate.opsForValue().set("test_file2", 1);
        for (int i = 0; i < 1000; i++) {
            Long test_file = redisTemplate.opsForSet().add("test_file4", UUID.randomUUID().toString());

            // Long increment = redisTemplate.opsForValue().increment("test_file2", 1);
        }
        System.out.println("结束时间：" + System.currentTimeMillis());

        //redisTemplate.opsForValue().increment(1,1);
        System.out.println(list.size());
        Set test_file = redisTemplate.opsForSet().members("test_file4");
        ICsvBeanWriter listWriter = new CsvBeanWriter(new FileWriter("/Users/zhangqiang/Desktop/test112.csv"),
                CsvPreference.STANDARD_PREFERENCE);
        final String[] header = new String[]{"couponcode"};
        //listWriter.writeHeader(header);
        Testpo testpo=new Testpo();
        for (Object o : test_file) {
            System.out.println(o);
            if(o!=null && !o.equals("")){
                testpo.setCouponcode(o.toString());
                listWriter.write(o);
            }

        }
        listWriter.close();
        //生成数据

    }

    @Test
    public void test1() {
        Set test_file4 = redisTemplate.opsForSet().members("test_fiwwwle4");
        System.out.println(test_file4.size());
    }
    @Test
    public void testZone(){
        TimeZone aDefault = TimeZone.getDefault();
        System.out.println(aDefault.getRawOffset());
    }


}
