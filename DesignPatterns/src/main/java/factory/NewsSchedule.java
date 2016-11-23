package factory;

/**
 * Created by tcf24 on 2016/11/23.
 */
public class NewsSchedule implements Schedule {
    @Override
    public void process() {
        System.out.println("this is news schedule...");
    }
}
