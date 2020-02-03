package com.utils.ExceptionUtil;

/**
 * @ProjectName: springboot-utils
 * @Package: com.utils.ExceptionUtil
 * @ClassName: MyException
 * @Author: zhangqiang
 * @Description: 自定义异常
 * @Date: 2019/12/17 5:55 下午
 * @Version: 1.0
 */
public class MyException extends Exception {

    public MyException(String message) {
        super(message);
    }
}
