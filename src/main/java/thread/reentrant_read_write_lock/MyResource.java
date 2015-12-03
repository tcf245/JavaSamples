package thread.reentrant_read_write_lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by pc9507 on 2015/12/3.
 * //自定义资源
 */
public class MyResource {
    //读写锁对象成员
    public ReadWriteLock lock = new ReentrantReadWriteLock();
    //被读写资源
    private String message = "inti..";
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
