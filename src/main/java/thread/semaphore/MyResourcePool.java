package thread.semaphore;

import java.util.concurrent.Semaphore;

/**
 * Created by pc9507 on 2015/12/3.
 * 自定义资源池类
 */
public class MyResourcePool {
    public Semaphore sp;
    public MyResourcePool(int count){
        sp = new Semaphore(count);
    }
}
