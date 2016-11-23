package factory;

/**
 * Created by tcf24 on 2016/11/23.
 */
public class TestMain {
    public static void main(String[] args) {
        ScheduleFactory factory = new NewScheduleFactory();
        Schedule schedule = factory.produce();
        schedule.process();

    }
}
