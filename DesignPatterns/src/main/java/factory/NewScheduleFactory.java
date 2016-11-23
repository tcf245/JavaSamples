package factory;

/**
 * Created by tcf24 on 2016/11/23.
 */
public class NewScheduleFactory implements ScheduleFactory {
    @Override
    public Schedule produce() {
        return new NewsSchedule();
    }
}
