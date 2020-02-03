package com.utils.async.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

/**
 * @ProjectName: springboot-utils
 * @Package: com.utils.async.config
 * @ClassName: AsyncConfig
 * @Author: zhangqiang
 * @Description: 多线程的配置
 * @Date: 2019/12/1 8:52 下午
 * @Version: 1.0
 */
@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {
    @Bean
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(10);
        threadPoolTaskExecutor.setMaxPoolSize(20);
        threadPoolTaskExecutor.setQueueCapacity(10);
        //等待任务在关机时完成--表明等待所有线程完成
        threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        threadPoolTaskExecutor.setAwaitTerminationSeconds(60);
        threadPoolTaskExecutor.setThreadNamePrefix("async-test");
        threadPoolTaskExecutor.setRejectedExecutionHandler(new rejecthandle());
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }

    @Bean
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {

        String threadName = Thread.currentThread().getName();
        System.out.println("出现-异常" + threadName);
        return null;
    }

    class rejecthandle implements RejectedExecutionHandler {
        private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(20);

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println("出现异常" + r);
            scheduledExecutorService.schedule(r, 10, TimeUnit.SECONDS);
        }
    }

}
