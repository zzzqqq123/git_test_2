package com.utils.theadutils.theadutils;

import com.utils.theadutils.theadpoolutils.ThreadPoolUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @ProjectName: springboot-utils
 * @Package: com.utils.theadutils.theadutils
 * @ClassName: CyclicBarrierTest
 * @Author: zhangqiang
 * @Description: 线程隔离
 * @Date: 2019/12/19 10:44 下午
 * @Version: 1.0
 * @Des CyclicBarrier 适合线程较下的情况，不适合与有队列的线程一起使用，因为线程是一直没有结束，都在互相等待
 */
public class CyclicBarrierTest {
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
    private static class Worker extends Thread {
        private CyclicBarrier cyclicBarrier;

        public Worker(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            super.run();

            try {
                count();
                System.out.println(Thread.currentThread().getName().toString() + "到达水闸门口");
                cyclicBarrier.await(10, TimeUnit.SECONDS);
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
    public static void main(String[] args) {

        int threadCount = 20;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(threadCount, new CountAll());
        for (int i = 0; i < 20; i++) {
            System.out.println("创建工作线程" + i);
            Worker worker = new Worker(cyclicBarrier);
            worker.start();

        }

    }
}
