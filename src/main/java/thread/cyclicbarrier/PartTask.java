package thread.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by pc9507 on 2015/12/3.
 * 子任务类
 * 设置障碍器，并且在run方法中设置障碍点
 */
public class PartTask implements Runnable {

    private String name;
    //设置障碍器
    private CyclicBarrier cb;
    private int duringTime;

    public PartTask(String name, CyclicBarrier cb, int duringTime) {
        this.name = name;
        this.cb = cb;
        this.duringTime = duringTime;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + "子任务开始执行");
            Thread.sleep(duringTime);
            System.out.println(this.name + "子任务执行结束");
            cb.await();  //设置障碍点
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
