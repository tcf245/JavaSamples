package thread.semaphore;

/**
 * Created by pc9507 on 2015/12/3.
 * 任务类
 */
public class MyTask implements Runnable {
    private String name;
    //资源池类的成员引用
    private MyResourcePool mrp;
    //该任务需要的资源数量
    private int count;

    public MyTask(String name, MyResourcePool mrp, int count) {
        this.name = name;
        this.mrp = mrp;
        this.count = count;
    }

    @Override
    public void run() {
        try {
            //申请指定数量的资源许可
            mrp.sp.acquire(count);
            System.out.println(this.name + "任务成功申请到了" + count + "个资源");
            //带着资源休眠
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            mrp.sp.release(count);
        }
    }
}
