package strategy;

/**
 * Created by tcf24 on 2016/11/19.
 */
public class TestMain {
    public static void main(String[] args) {
        Duck greenDuck = new GreenDuck();
        greenDuck.display();

        Duck frakeDuck = new FrakeDuck();
        frakeDuck.display();

    }

}
