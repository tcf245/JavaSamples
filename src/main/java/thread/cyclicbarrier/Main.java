package thread.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by pc9507 on 2015/12/3.
 * 主线程设置了障碍器并且指定了最终任务，然后在线程池创建五个子任务
 * 当五个子任务全部执行至障碍点后，指定的最终任务执行
 * 最后关闭线程池
 */
public class Main {
    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(5,new FinalTask());

        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        for (int i =0 ;i < 5;i++){
            threadPool.execute(new PartTask("Task" + i,cb,1000));
        }
        threadPool.shutdown();
    }
}
