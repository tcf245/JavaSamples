package thread.reentrantlock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by pc9507 on 2015/12/3.
 * 初始账户余额 50
 * 两个线程启动执行
 * User1取款任务首先获得锁并执行，取款200，但是由于余额不足，释放锁并等待下一次尝试
 * User2 获得锁并执行，存款200成功 ，账户余额250
 * User1 得到锁，进行第二次尝试，取款200成功，余额50
 */
public class LockTest {
    public static void main(String[] args) {
        MyCount mc = new MyCount(50);
        Lock countLock = new ReentrantLock();
        //创建线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        threadPool.execute(new MyUser("user1",countLock,mc,-200));
        threadPool.execute(new MyUser("user2",countLock,mc,200));
        //关闭线程池
        threadPool.shutdown();
    }
}
