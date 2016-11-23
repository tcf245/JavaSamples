package wrapper;

/**
 * Created by tcf24 on 2016/11/23.
 */
public class TestMain {
    public static void main(String[] args) {
        Decorator mocha = new Decorator();
        mocha = new CoffeBean(mocha);
        mocha = new Milk(mocha);
        mocha = new Water(mocha);

        mocha.method();
        System.out.println(mocha.getPrice());
    }
}
