package com.utils.ExceptionUtil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @ProjectName: springboot-utils
 * @Package: com.utils.ExceptionUtil
 * @ClassName: GlobalExceptionhandle
 * @Author: zhangqiang
 * @Description: 全局异常处理
 * @Date: 2019/12/17 4:00 下午
 * @Version: 1.0
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionhandle {
    @ExceptionHandler({MyException.class})
    public String  handleException(){
        System.out.println("出现异常");
        return "出现异常";

    }
    @ExceptionHandler({NoHandlerFoundException.class})
    public String handle404Exception(){
      log.info("Exception={}","NoHandlerFoundException");
        return "404";

    }
}
