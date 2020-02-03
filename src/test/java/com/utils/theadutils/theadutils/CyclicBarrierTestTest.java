package com.utils.theadutils.theadutils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @ProjectName: springboot-utils
 * @Package: com.utils.theadutils.theadutils
 * @ClassName: CyclicBarrierTestTest
 * @Author: zhangqiang
 * @Description:
 * @Date: 2019/12/19 10:44 下午
 * @Version: 1.0
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class CyclicBarrierTestTest {
    //@Test
    public void contextLoads() {
        int threadCount = 3;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(threadCount);

        for (int i = 0; i < threadCount; i++) {
            System.out.println("创建工作线程" + i);
            Thread thread = new Thread(new TestThread(cyclicBarrier));
            thread.start();
        }
        System.out.println("线程完成");


    }

    class TestThread implements Runnable {
        private CyclicBarrier cyclicBarrier;

        public TestThread(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + "开始等待其他线程");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + "开始执行");
                // 工作线程开始处理，这里用Thread.sleep()来模拟业务处理
                //Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "执行完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }


        }
    }

}
