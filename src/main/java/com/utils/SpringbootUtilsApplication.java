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
<<<<<<< HEAD
        //test2
=======
>>>>>>> 0d1fa24a32c231f80839daac378b9d035eed5b5b
    }


}
