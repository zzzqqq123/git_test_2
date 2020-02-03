package com.utils.propertiesutils;

import com.utils.async.service.AsyncTaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ProjectName: springboot-utils
 * @Package: com.utils.propertiesutils
 * @ClassName: AsyncTest
 * @Author: zhangqiang
 * @Description: test
 * @Date: 2019/12/1 9:46 下午
 * @Version: 1.0
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class AsyncTest {
    @Autowired
    private AsyncTaskService asyncTaskService;
    //@Test
    public void test() throws InterruptedException {
        for (int i = 0; i <40 ; i++) {
            asyncTaskService.ezecuteAsynctask(i);
        }
        Thread.sleep(1000000);
    }
}
