package com.utils.theadutils.theadpoolutils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @ProjectName: springboot-utils
 * @Package: com.utils.theadutils.theadpoolutils
 * @ClassName: ThreadPoolUtilsTest
 * @Author: zhangqiang
 * @Description: 线程池测试
 * @Date: 2019/10/24 10:28 下午
 * @Version: 1.0
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class ThreadPoolUtilsTest {
    static AtomicInteger atomicInteger = new AtomicInteger();

    /**
     * @param
     * @Method test
     * @Author zhangqiang
     * @Version 1.0
     * @Description queSize=10 corePoolSize=1 maxPoolsize=1  当前线程数量为13 当不执行future.get() 方法的时候会发生线程拒绝异常，当
     * 执行时 会进行阻塞，线程会一个一个执行（不论核心线程数有多少个）
     * @Return void
     * @Exception
     * @Date 2019/10/25
     */
   /* @Test
    public void test() throws ExecutionException, InterruptedException {

        ThreadPoolUtils threadPoolUtils = ThreadPoolUtils.init();
        for (int i = 0; i < 13; i++) {
            MyThread myThread = new MyThread();
            ThreadGroup threadGroup = new ThreadGroup("test--1");
            Thread thread = new Thread(threadGroup, myThread);
            threadPoolUtils.execute(thread);
        }
        boolean flag = true;

    }*/

    /**
     * @param
     * @Method test2
     * @Author zhangqiang
     * @Version 1.0
     * @Description 12 个线程大概执行时间是6000s 核心线程数2，当最大线程数量为4的时候，每次同时执行线程个数为4
     * @Return void
     * @Exception
     * @Date 2019/10/25
     */
    //@Test
    public void test2() throws InterruptedException, ExecutionException, TimeoutException {

        ThreadPoolUtils threadPoolUtils = ThreadPoolUtils.init();
        System.out.println();
        List<Callable<String>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            MyFuther myFuther = new MyFuther();
            list.add(myFuther);
        }
        System.out.println(System.currentTimeMillis());
        List<Future<String>> futures = threadPoolUtils.execute(list);
        for (Future<String> future : futures) {
            System.out.println(future.get());
        }

    }

    class MyFuther implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.out.println(Thread.currentThread().getName());
            System.out.println("开始执行");
            Thread.sleep(1000);
            return "你好";
        }
    }

    class MyThread implements Runnable {
        private List list;

        public MyThread(List list) {
            this.list = list;
        }

        @Override
        public void run() {
            System.out.println(list.size());
            try {
                for (int i = 0; i < list.size(); i++) {
                    Thread.sleep(6000);
                    System.out.println(list.size());
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("run");

        }
    }

    public void out() {
        System.out.println("tt---");
    }

   /* @Test
    public void testxx() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            executorService.execute(new MyThread());
        }
        executorService.shutdown();
        while (true) {
            //executorService.isTerminated()
            //awaitTermination
            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                System.out.println("线程池中还有线程");
            }
        }

    }*/

    //@Test
    public void testThreadArr() throws InterruptedException {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            list.add(i + "");
        }
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        executorService.execute(new MyThread(list));
        list=new ArrayList<>();
        Thread.sleep(100000);


    }


}
