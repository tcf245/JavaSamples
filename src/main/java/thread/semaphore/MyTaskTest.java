package thread.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by pc9507 on 2015/12/3.
 * 创建一个数量为10的资源池，并且通过线程池启动三个任务，三个任务分别申请了8，4，1个资源
 * 1.首先任务1申请到了八个资源开始执行
 * 2.任务2需要4个资源，但此时只有两个，无法执行，进入等待状态
 * 3.任务3需要1个资源，此时资源池中有两个，满足申请，任务3执行
 * 4.任务1执行完毕，归还8个资源，任务2申请成功并执行
 *
 */
public class MyTaskTest {
    public static void main(String[] args) {
        //创建资源池对象，资源数量为10
        MyResourcePool mrp = new MyResourcePool(10);
        //创建线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(4);

        threadPool.execute(new MyTask("Task1",mrp,8));
        threadPool.execute(new MyTask("Task2",mrp,4));
        threadPool.execute(new MyTask("Task3",mrp,1));
        //关闭线程池
        threadPool.shutdown();
    }
}
