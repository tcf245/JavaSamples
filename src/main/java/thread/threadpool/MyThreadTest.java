package thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by pc9507 on 2015/12/2.
 * newFixedThreadPool 固定尺寸线程池
 * 线程池固定尺寸为2，表示其中只有两个待命线程，所以同一时间内只能执行两个任务
 * 其中一个结束后  Mt3才能开始
 *
 * 使用线程池的程序在执行完所有任务后并不会自动退出 而是继续等待任务。
 * 使用threadPool.shutdown()方法后，程序在执行完任务后退出
 */
public class MyThreadTest {

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        MyThread Mt1 = new MyThread("mt1");
        MyThread Mt2 = new MyThread("mt2");
        MyThread Mt3 = new MyThread("mt3");

        threadPool.execute(Mt1);
        threadPool.execute(Mt2);
        threadPool.execute(Mt3);

        threadPool.shutdown();
    }
}
