package com.utils.theadutils.theadutils;

import com.utils.theadutils.theadpoolutils.ThreadPoolUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @ProjectName: springboot-utils
 * @Package: com.utils.theadutils.theadutils
 * @ClassName: CountDownLatchTest
 * @Author: zhangqiang
 * @Description: CountDownLatch 测试
 * @Date: 2019/12/20 11:43 上午
 * @Version: 1.0
 */
public class CountDownLatchTest {
    static Map<String, Integer> map = new ConcurrentHashMap<>();

    //线程都到达闸门后，执行的线程
    static class CountAll implements Runnable {

        @Override
        public void run() {
            System.out.println("开始计数工作");
            int sum = 0;
            for (Map.Entry<String, Integer> entry : map.entrySet()) {

                System.out.println(entry.getKey() + ":" + entry.getValue());
                sum += entry.getValue();
            }
            System.out.println("total:" + sum);
        }
    }

    //子线程
    private static class Worker2 extends Thread {
        private CountDownLatch countDownLatch;

        public Worker2(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            super.run();

            try {
                count();
                System.out.println(Thread.currentThread().getName().toString() + "到达水闸门口");
                countDownLatch.countDown();
                long count = countDownLatch.getCount();
                if(count==0){
                    System.out.println("线程执行完成");
                }
                System.out.println("----");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //子线程的业务
    public static void count() {
        int sum = 0;
        for (int i = 0; i < 1000; i++) {
            map.put(Thread.currentThread().getName().toString(), sum = sum + i);
        }
    }

    // 测试
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolUtils threadPoolUtils = ThreadPoolUtils.init();
        int threadCount = 20;
       // CyclicBarrier cyclicBarrier = new CyclicBarrier(threadCount, new CyclicBarrierTest.CountAll());
        CountDownLatch countDownLatch=new CountDownLatch(20);
        for (int i = 0; i < 20; i++) {
            System.out.println("创建工作线程" + i);
            threadPoolUtils.execute(new Worker2(countDownLatch));
        }
        countDownLatch.await();
        System.out.println("完全执行完成");

    }
}
