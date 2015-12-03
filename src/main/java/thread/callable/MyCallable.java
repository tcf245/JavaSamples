package thread.callable;

import java.util.concurrent.Callable;

/**
 * Created by pc9507 on 2015/12/3.
 */
public class MyCallable implements Callable{
    private String taskName;
    public MyCallable(String name) {
        this.taskName = name;
    }
    @Override
    public Object call() throws Exception {
        System.out.println(this.taskName + " 任务执行。。" );
        return "恭喜你，任务执行成功，这是返回消息。";
    }
}
