package thread.reentrantlock;

import java.util.concurrent.locks.Lock;

/**
 * Created by pc9507 on 2015/12/3.
 * 表示对账户进行操作的用户对象
 */
public class MyUser implements Runnable {
    private String userName;
    //锁对象引用
    private Lock countLock;
    //账户对象引用
    private MyCount mc;
    //操作数额(正数表示存款，负数表示取款)
    private int taskSum;
    public MyUser(String userName, Lock countLock, MyCount mc, int taskSum) {
        this.userName = userName;
        this.countLock = countLock;
        this.mc = mc;
        this.taskSum = taskSum;
    }
    @Override
    public void run() {
        //操作是否成功标志
        boolean okFlag = false;
        while (!okFlag) {
            //操作不成功继续尝试
            try {
                //操作过程中，对账户进行加锁
                countLock.lock();
                if (taskSum < 0) { //取款操作
                    if (taskSum + mc.getCount() >= 0) {
                        mc.setCount(mc.getCount() + taskSum);
                        System.out.println(" 成功取款 " + (-taskSum) + "元，余额为：" + mc.getCount() + "元.");
                        okFlag = true;
                    }
                }
                if (taskSum > 0) { //存款操作
                    mc.setCount(mc.getCount() + taskSum);
                    System.out.println("成功存款" + taskSum + "元，余额为: " + mc.getCount() + "元");
                    okFlag = true;
                }
            } finally {
                countLock.unlock(); //操作完成，释放锁
            }
            if (okFlag == true) {
                break;
            }
            try {
                System.out.println(userName + " ：余额不足。");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
