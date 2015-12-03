package thread.priorityqueue;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by pc9507 on 2015/12/3.
 * 主方法中使用了两个优先级队列，一个按元素自然顺序排序，一个按照指定比较器排序
 */
public class Main {

    //访问队列所有元素方法
    public static void scanQueue(Queue queue) {
        Object o = queue.peek();
        while (o != null) {
            System.out.print(o);
            o = queue.poll();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        SortedSet sortSet = new TreeSet();
        //向集合中添加10个元素
        for (int i = 0; i < 10; i++) {
            sortSet.add(new MyElement(((int) Math.round(Math.random() * 100)), "第" + i + "个"));
        }
        //将集合元素添加到队列中
        Queue pq1 = new PriorityQueue<>(sortSet);
        System.out.println("===对第一个优先级队列访问(优先级从小到大)===");
        scanQueue(pq1);

        Queue pq2 = new PriorityQueue<>(10, new MyComparator());
        for (int i = 0; i < 10; i++) {
            pq2.offer(new MyElement(((int) Math.round(Math.random() * 100)), "第" + i + "个"));
        }
        System.out.println("===对第二个优先级队列访问(优先级从大到小)===");
        scanQueue(pq2);

    }
}
