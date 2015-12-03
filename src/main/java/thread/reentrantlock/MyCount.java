package thread.reentrantlock;

/**
 * Created by pc9507 on 2015/12/3.
 * 需要避免并发访问的账户资源(互斥资源)
 */
public class MyCount {
    //账户余额
    private int count;
    public MyCount(int count) {
        this.count = count;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
}
