package thread.cyclicbarrier;

/**
 * Created by pc9507 on 2015/12/3.
 * 最终任务
 */
public class FinalTask implements Runnable{
    @Override
    public void run() {
        System.out.println("FinalTask. 执行完成..");
    }
}
