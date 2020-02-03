package com.utils;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import com.utils.codecover.TestCodeCover;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootUtilsApplicationTests {
    private static final Logger logger = LoggerFactory.getLogger(SpringbootUtilsApplicationTests.class);

    int i = 0;

    @Test
    public void contextLoads() {
        logger.info("111");

    }

    @Test
    public void deadLoop() {
        for (; ; ) {
            System.out.println(++i);
            if (i > 100) {
                break;
            }
        }

    }

    @Autowired
    private TestCodeCover testCodeCover;

    @Test
    public void tet() {

        testCodeCover.tets2();
    }

    @Test
    public void testObjectNotNull() {
       String filePath="/1/2/3.3/5.txt";
        String s = StrUtil.subBefore(filePath, ".", true);
        String s1 = StrUtil.subAfter(filePath, ".", true);
        System.out.println(s);
        System.out.println(s1);


    }

}
