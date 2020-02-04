package com.utils;

import com.utils.ExceptionUtil.MyException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("test")
public class SpringbootUtilsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootUtilsApplication.class, args);
    }
    @GetMapping("t1")
    public void test() throws Exception {
        throw  new MyException("fa");
        //test
        //test2
        //add-test1
        //add-test2
        //add-test3
        //add-revert
        //add-revert-test
        //add-revert-not-commit




    }


}
