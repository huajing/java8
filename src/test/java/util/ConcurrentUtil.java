package util;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description TODO
 * @Author chenck
 * @Date 2022/5/26 9:33
 * @Version 1.0
 **/

public class ConcurrentUtil {
    public static void concurrentExe(int nThreads,   final int forCount, final BizClass bizClass){
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
        CountDownLatch countDownLatch = new CountDownLatch(nThreads);
        for (int j = 0; j < nThreads; j++) {
            executorService.execute(()->{
                for (int i = 0; i < forCount; i++) {
                    bizClass.doBiz();
                }
                countDownLatch.countDown();
            });
        }
        try {
            countDownLatch.await();
            executorService.shutdownNow();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public interface BizClass{
        void doBiz();
    }
}
