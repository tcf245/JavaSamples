package thread.reentrant_read_write_lock;

/**
 * Created by pc9507 on 2015/12/3.
 * //进行读写任务的用户类
 */
public class MyUser implements Runnable {
    private String userName;
    //操作代码(0表示读操作，1表示写操作)
    private int functionCode;
    private String message;
    //资源引用对象
    private MyResource mr;

    public MyUser(String userName, int functionCode, String message, MyResource mr) {
        this.userName = userName;
        this.functionCode = functionCode;
        this.message = message;
        this.mr = mr;
    }

    @Override
    public void run() {
        if (functionCode == 0) { // 读任务
            try {
                //获得读锁
                mr.lock.readLock().lock();
                System.out.println(userName + "进行读操作，读取内容为" + mr.getMessage());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                mr.lock.readLock().unlock(); //释放锁
            }
        } else {                   //写任务
            try {
                //获得写锁
                mr.lock.writeLock().lock();
                mr.setMessage(message);
                System.out.println(userName + "进行写操作，写入后内容为" + mr.getMessage());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally{
                mr.lock.writeLock().unlock();
            }
        }
    }
}
