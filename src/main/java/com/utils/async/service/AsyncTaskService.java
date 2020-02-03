package com.utils.async.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: springboot-utils
 * @Package: com.utils.async.service
 * @ClassName: AsyncTaskService
 * @Author: zhangqiang
 * @Description: 异步任务service
 * @Date: 2019/12/1 9:43 下午
 * @Version: 1.0
 */
@Service
public class AsyncTaskService {
    @Async
    public void ezecuteAsynctask(Integer i) {
        System.out.println("线程：" + Thread.currentThread().getName()+"-"+i);
    }
}
