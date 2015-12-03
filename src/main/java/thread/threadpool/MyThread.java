package thread.threadpool;

/**
 * Created by pc9507 on 2015/12/2.
 */
public class MyThread implements Runnable{
    private String name;
    public MyThread(String name) {
        this.name = name;
    }
    @Override
    public void run() {
        System.out.println("===Task " + this.name + " begin");
        for (int i = 0;i < 10 ;i++){
            System.out.print( "[" + this.name + "_" + i +"];");
        }
        System.out.println("===Task " + this.name + " end.");
    }
}
