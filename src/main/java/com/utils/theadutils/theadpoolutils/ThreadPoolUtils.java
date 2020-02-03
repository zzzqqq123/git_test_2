package com.utils.theadutils.theadpoolutils;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ProjectName: springboot-utils
 * @Package: com.utils.theadutils.theadpoolutils
 * @ClassName: ThreadPoolUtils
 * @Author: zhangqiang
 * @Description: 线程池工具类
 * @Date: 2019/10/24 6:53 下午
 * @Version: 1.0
 */
@Data
public class ThreadPoolUtils {
    private static final Logger logger = LoggerFactory.getLogger(ThreadPoolUtils.class);
    private static final int corePoolSize = 10;
    //maxPoolSize>=corePoolSize,否则报错
    private static final int maxPoolSize = 20;
    private static final long keepAliveTime = 6000;
    private static final int workQue = 100000;
    private static TimeUnit timeUnit = TimeUnit.SECONDS;
    private ThreadPoolExecutor threadPoolExecutor;
    private static BlockingDeque<Runnable> queue;
    private static volatile ThreadPoolUtils threadPoolUtils;
    /**
     * @param corePoolSize
     * @param maxPoolSize
     * @param keepAliveTime 当线程数量超过workQue+corePoolSize 的时候会创建新的线程直至线程数量超过最大线程数，新创建的非核心线程会在空闲时间到达keepalive后销毁
     * @param timeUnit
     * @param queue
     * @Method ThreadPoolUtils
     * @Author zhangqiang
     * @Version 1.0
     * @Description
     * @Return
     * @Exception
     * @Date 2019/10/24
     */
    /**
     * ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。
     * ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。
     * ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
     * ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务（调用当前的线程去执行）
     * 核心线程数的判断，如果是I/O密集型的设置为2*cpu
     * 如果是cpu密集型的设置为cpu+1
     */
    public ThreadPoolUtils(int corePoolSize, int maxPoolSize, long keepAliveTime, TimeUnit timeUnit, BlockingDeque<Runnable> queue, ThreadFactory threadFactory) {
        threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, timeUnit, queue, threadFactory, new ThreadPoolExecutor.CallerRunsPolicy());
    }

    /**
     * @param
     * @Method init
     * @Author zhangqiang
     * @Version 1.0
     * @Description 初始化线程池
     * @Return com.utils.theadutils.theadpoolutils.ThreadPoolUtils
     * @Exception
     * @Date 2019/10/24
     */
    public static ThreadPoolUtils init() {

        if (threadPoolUtils == null) {
            synchronized (ThreadPoolUtils.class) {
                if (threadPoolUtils == null) {
                    queue = new LinkedBlockingDeque(workQue);
                    ThreadFactory threadFactory = new DefaultThreadFactory(null, "threadorefix");
                    threadPoolUtils = new ThreadPoolUtils(corePoolSize, maxPoolSize, keepAliveTime, timeUnit, queue, threadFactory);
                }
            }
        }
        return threadPoolUtils;
    }
    public int getCount(){
        return threadPoolExecutor.getActiveCount();
    }
    public int getqueue(){
        BlockingQueue<Runnable> queue = threadPoolExecutor.getQueue();
        System.out.println(queue.size());
        return queue.size();
    }

    /**
     * @param
     * @Method awaitTermination
     * @Author zhangqiang
     * @Version 1.0
     * @Description 判断线程池中的线程是否完成，如果完成则关闭线程池
     * 一般自开发过程中，采用的是单例模式，所以一般不用销毁，根据实际的情况确定
     * @Return void
     * @Exception
     * @Date 2019/11/25
     */
    public void awaitTermination() throws InterruptedException {
        long timeOut = 60;
        threadPoolExecutor.shutdown();
        boolean result = threadPoolExecutor.awaitTermination(timeOut, timeUnit);
        if (result) {
            logger.info("线程池中线程等待" + timeOut + "仍然有线程执行，强制关闭!");
            threadPoolExecutor.shutdownNow();
        }
    }

    /**
     * @param t
     * @Method execute
     * @Author zhangqiang
     * @Version 1.0
     * @Description execute 没有返回值
     * @Return void
     * @Exception
     * @Date 2019/11/25
     */
    public void execute(Runnable t) {
        threadPoolExecutor.execute(t);
    }

    /**
     * @param t
     * @Method execute
     * @Author zhangqiang
     * @Version 1.0
     * @Description execute没有返回值
     * @Return void
     * @Exception
     * @Date 2019/11/25
     */
    public void execute(Thread t) {
        threadPoolExecutor.execute(t);
    }

    public <T> List<Future<T>> execute(Collection<? extends Callable<T>> tasks) throws InterruptedException {
        List<Future<T>> futures = threadPoolExecutor.invokeAll(tasks);
        return futures;
    }

    /**
     * @param task
     * @Method excute
     * @Author zhangqiang
     * @Version 1.0
     * @Description 有返回值
     * @Return java.util.concurrent.Future<T>
     * @Exception
     * @Date 2019/11/25
     */
    public <T> Future<T> excute(Callable<T> task) {

        return threadPoolExecutor.submit(task);
    }

    /**
     * @param runnable
     * @Method submit
     * @Author zhangqiang
     * @Version 1.0
     * @Description 有返回值
     * @Return java.util.concurrent.Future<?>
     * @Exception
     * @Date 2019/11/25
     */
    public Future<?> submit(Runnable runnable) {
        Future<?> future = threadPoolExecutor.submit(runnable);
        return future;

    }

    /**
     * @param null
     * @Method
     * @Author zhangqiang
     * @Version 1.0
     * @Description 自定义线程拒绝策略
     * @Return
     * @Exception
     * @Date 2019/11/25
     */
    static class MyThreadRejecthandle implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            int corePoolSize = executor.getCorePoolSize();
            System.out.println(corePoolSize);
        }
    }

    /**
     * @param groupName
     * @Method poolExistActiveThread
     * @Author zhangqiang
     * @Version 1.0
     * @Description 根据线程组名称判断队列中是否还存在线程
     * @Return boolean
     * @Exception
     * @Date 2019/11/25
     */
    public boolean poolExistActiveThread(String groupName) {
        BlockingQueue<Runnable> queue = threadPoolExecutor.getQueue();
        for (Runnable runnable : queue) {
            Thread thread = (Thread) runnable;
            if (thread.getThreadGroup().toString().indexOf(groupName) > 0) {
                System.out.println("quesize" + queue.size());
                System.out.println(thread.getThreadGroup());
                return true;
            }
        }
        return false;
    }

    /**
     * @Method
     * @Author zhangqiang
     * @Version 1.0
     * @Description 设置线程工厂（目前没有使用）
     * 注意：在每次线程调用的时候才开始执，在线程进入对列的时候是不执行的
     * @Exception
     * @Date 2019/10/24
     */
    static class DefaultThreadFactory implements ThreadFactory {

        private final AtomicInteger pollNumber = new AtomicInteger(1);
        private final ThreadGroup threadGroup;
        private final AtomicInteger thradNumber = new AtomicInteger(1);
        private final String namePrefix;

        /**
         * @Method
         * @Author zhangqiang
         * @Version 1.0
         * @Description 创建线程，以及设置线程相关的属性
         * @Date 2019/10/25
         */
        public DefaultThreadFactory(ThreadGroup threadGroup, String namePrefix) {
            SecurityManager s = System.getSecurityManager();
            this.threadGroup = s != null ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            this.namePrefix = "CSPOOL-" + pollNumber.getAndIncrement() + "thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            System.out.println("组合线程");
            Thread thread = new Thread(threadGroup, r, namePrefix + thradNumber.getAndIncrement(), 0);
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            //设置线程的优先级 10表示最高优先级，1表示最低优先级
            if (thread.getPriority() != Thread.NORM_PRIORITY) {
                thread.setPriority(Thread.NORM_PRIORITY);
            }
            return thread;
        }
    }
}
