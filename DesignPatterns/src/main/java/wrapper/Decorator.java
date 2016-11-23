package wrapper;

/**
 * Created by tcf24 on 2016/11/23.
 */
public class Decorator implements Drink {


    @Override
    public void method() {
        System.out.println("peiliao : ");
    }

    @Override
    public double getPrice() {
        return 0;
    }
}
