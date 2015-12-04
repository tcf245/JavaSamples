package thread.customthreadpool;

/**
 * Created by pc9507 on 2015/12/3.
 */
public class MyTask implements Runnable {
    private String name;
    public MyTask(String name) {
        this.name = name;
    }
    @Override
    public void run() {
        System.out.println("[任务 " + this.name + " 开始执行]");
        for (int i = 0;i < 10 ;i++){
            System.out.print("[" + this.name + "_" + i + "]");
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("[任务 " + this.name + " 执行结束]");
    }
}
