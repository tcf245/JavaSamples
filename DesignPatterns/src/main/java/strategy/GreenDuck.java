package strategy;

/**
 * Created by tcf24 on 2016/11/19.
 */
public class GreenDuck extends Duck {

    public GreenDuck() {
        this.setFlyBehavior(new FlyingWithWings());
        this.setQuackBehavior(new QuackGua());
    }

    public void showType() {
        System.out.println("this is a GreenDuck..");
    }
}
