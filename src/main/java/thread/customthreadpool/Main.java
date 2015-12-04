package thread.customthreadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by pc9507 on 2015/12/3.
 *线程池最大尺寸是4，标准尺寸是2，工作等待队列容量为3，
 * 因此根据规则不会创建新的线程去执行任务，同时只有两个任务执行，
 * 其他任务在队列中等待，知道有任务结束在开始执行
 *
 * 若将等待容量设置为1，，线程池标准尺寸为2，最大尺寸是4，
 * 根据规则将会创建新的线程来执行任务，此时线程池中实际大小为3
 * 一个任务在队列中等待。等待有一个任务之行结束够，在开始执行。
 */
public class Main {
    public static void main(String[] args) {
        //等待队列容量
        BlockingQueue queue = new ArrayBlockingQueue(2);
        //标准尺寸、最大容量、空闲线程存活时间、时间单位、等待队列
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 4, 100, TimeUnit.MILLISECONDS, queue);
        threadPool.execute(new MyTask("Mt1"));
        threadPool.execute(new MyTask("Mt2"));
        threadPool.execute(new MyTask("Mt3"));
        threadPool.execute(new MyTask("Mt4"));
        System.out.println("当前线程池实际大小为：" + threadPool.getPoolSize());
        //关闭线程池
        threadPool.shutdown();
    }
}
