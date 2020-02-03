package com.utils.logback;

import com.utils.ExceptionUtil.MyException;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ProjectName: springboot-utils
 * @Package: com.utils.logback
 * @ClassName: LogTestTest
 * @Author: zhangqiang
 * @Description:
 * @Date: 2019/12/17 11:52 上午
 * @Version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LogTestTest {

    @Test
    public void contextLoads() {
       log.error("-------------------------123");
    }
    @Test
    public void test2() throws MyException {
        throw new MyException("niha");

    }

}
