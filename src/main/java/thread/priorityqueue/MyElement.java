package thread.priorityqueue;

/**
 * Created by pc9507 on 2015/12/3.
 */
public class MyElement implements Comparable {
    //表示优先级
    int priority;
    String content;
    public MyElement(int priority, String content) {
        this.priority = priority;
        this.content = content;
    }
    //实现自然顺序的compareTo方法
    @Override
    public int compareTo(Object o) {
        //按优先级从小到大排列
        return this.priority - ((MyElement)o).priority;
    }
    @Override
    public String toString() {
        return "[优先级为：" + priority + ",内容为" + content + "]";
    }
}
