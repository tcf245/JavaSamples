package thread.reentrant_read_write_lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by pc9507 on 2015/12/3.
 */
public class ReadWriteLockTest {
    public static void main(String[] args) {
        MyResource mr = new MyResource();
        //创建线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(4);
        //执行任务
        threadPool.execute(new MyUser("User1",0,null,mr));
        threadPool.execute(new MyUser("User2",1,"Hello_World!!!",mr));
        threadPool.execute(new MyUser("User3",0,null,mr));
        //关闭线程池
        threadPool.shutdown();
    }
}
