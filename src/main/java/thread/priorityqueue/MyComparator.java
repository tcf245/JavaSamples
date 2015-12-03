package thread.priorityqueue;

import java.util.Comparator;

/**
 * Created by pc9507 on 2015/12/3.
 * 自定义比较器
 */
public class MyComparator implements Comparator{
    @Override
    public int compare(Object o1, Object o2) {
        //按优先级从大到小排列
        return ((MyElement)o2).priority - ((MyElement)o1).priority;
    }
}
