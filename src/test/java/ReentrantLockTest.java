import com.sun.corba.se.impl.resolver.SplitLocalResolverImpl;
import util.ConcurrentUtil;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @Description TODO
 * @Author chenck
 * @Date 2022/5/26 9:17
 * @Version 1.0
 **/

public class ReentrantLockTest {
    private Sync sync;
    private ReentrantLockTest(){
        sync = new FairSync();
    }

    public void lock(){
        if(sync.tryAcquire(1)){
            return;
        }
        sync.acquire(1);
    }

    public void unlock(){
        sync.release(1);
    }
    /**
     * 核心state
     */
    static class Sync extends AbstractQueuedSynchronizer{
        @Override
        protected boolean tryAcquire(int arg) {
            return super.tryAcquire(arg);
        }
    }

    static class FairSync extends Sync{

    }

    static class UnfairSync extends Sync{
    }

    static class IncClass{
        private int idx;
        public void inc(){
            idx++;
        }
    }

    public static void main(String[] args) {
        final IncClass incClass = new IncClass();
        final ReentrantLockTest lockTest = new ReentrantLockTest();
        final AtomicInteger atomicInteger = new AtomicInteger();
        ConcurrentUtil.concurrentExe(10, 1000, ()->{
            lockTest.lock();
            try {
                incClass.inc();
                atomicInteger.incrementAndGet();
            }catch (Exception ex){
                ex.printStackTrace();
            }finally {
                lockTest.unlock();
            }

        });
        System.out.println("atomicInteger:" + atomicInteger.get());
        System.out.println("incClass.inc:" + incClass.idx);
    }

}
