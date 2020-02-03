package com.utils.ExceptionUtil;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: springboot-utils
 * @Package: com.utils.ExceptionUtil
 * @ClassName: ErrorExceptionController
 * @Author: zhangqiang
 * @Description: 错误处理
 * @Date: 2019/12/17 4:01 下午
 * @Version: 1.0
 * @deprecated 当服务启动，但是服务不存在的时候会出现这种状况 例如访问http://localhost:9999/test/t2（应该是出现404的问题）
 * springboot对404的问题不进行处理
 * ****************
 * spring.mvc.throw-exception-if-no-handler-found=true
 * spring.resources.add-mappings=false
 * 可以走异常处理，使用统一异常处理捕获 NoHandlerFoundException
 * ***************
 *
 */
@RestController
public class ErrorExceptionController implements ErrorController {
    @Override
    public String getErrorPath() {
        return "/error";
    }

    @GetMapping("/error")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Object errorHandle() {
        return "系统异常，请稍后访问";
    }
}
