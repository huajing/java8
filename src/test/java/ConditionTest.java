import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description TODO
 * @Author chenck
 * @Date 2022/5/23 8:18
 * @Version 1.0
 **/

public class ConditionTest {
    public static void main(String[] args) throws InterruptedException {
        final ReentrantLock lock = new ReentrantLock();
        final Condition condition = lock.newCondition();

        Thread1 thread1 = new Thread1(lock, condition);
        thread1.start();

        Thread.sleep(2000);

        Thread2 thread2 = new Thread2(lock, condition);
        thread2.start();

        Thread.sleep(4000);
        System.out.println("5 main over");
    }

    public static class Thread1 extends Thread{
        private ReentrantLock lock;
        private Condition condition;
        public Thread1(ReentrantLock lock, Condition condition){
            this.lock = lock;
            this.condition = condition;
        }
        @Override
        public void run() {
            try {
                System.out.println("1 start wait");
                lock.lock();
                //lock.lockInterruptibly(); 可以
                condition.await();
                System.out.println("4 await over");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }

    public static class Thread2 extends Thread{
        private ReentrantLock lock;
        private Condition condition;
        public Thread2(ReentrantLock lock, Condition condition){
            this.lock = lock;
            this.condition = condition;
        }

        @Override
        public void run() {
            try {
                System.out.println("2 start signal");
                lock.lock();
                condition.signal();
                System.out.println("3 over signal");
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }
}
