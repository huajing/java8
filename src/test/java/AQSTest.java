import util.ConcurrentUtil;

import java.util.Currency;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @Description TODO
 * @Author chenck
 * @Date 2022/5/24 9:36
 * @Version 1.0
 **/

public class AQSTest extends AbstractQueuedSynchronizer {
    private volatile int hasVolatileIdx;
    private int noVolatileIdx;

    public AQSTest(){
        setState(0);
    }

    public void inc(){
        hasVolatileIdx++;
        noVolatileIdx++;
        while (true) {//不自旋，则得不得正确的数据
            int state = this.getState();
            if(compareAndSetState(state, ++state)){//返回true表示设置成功
                break;
            }
        }
    }

    public int getHasVolatileIdx() {
        return hasVolatileIdx;
    }

    public int getNoVolatileIdx() {
        return noVolatileIdx;
    }

    public int getCount(){
        return this.getState();
    }

    public static void main(String[] args) throws InterruptedException {
        //线程安全值对比
        AtomicInteger atomicInteger = new AtomicInteger();

        AQSTest myAQS = new AQSTest();

        ConcurrentUtil.concurrentExe(10, 10000, ()->{
            myAQS.inc();
            atomicInteger.incrementAndGet();
        });
        System.out.println(myAQS.getCount());
        System.out.println(atomicInteger.get());
        System.out.println("hasVolatileIdx:" + myAQS.getHasVolatileIdx());
        System.out.println("noVolatileIdx:" + myAQS.getNoVolatileIdx());
    }
}
