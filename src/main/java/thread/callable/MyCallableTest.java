package thread.callable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by pc9507 on 2015/12/3.
 */
public class MyCallableTest {
    public static void main(String[] args) {
        //创建单任务线程池
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        //创建MyCallable实例
        MyCallable mc = new MyCallable("mc1");

        /**
         * 执行mc任务。
         * mc任务实现了Callable接口，需要使用ExecutorService的submit方法执行。
         * 返回值为Future接口类型的引用。
         */
        Future future = threadPool.submit(mc);
        try {
            //打印mc任务执行后的返回值
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        threadPool.shutdown();
    }
}
