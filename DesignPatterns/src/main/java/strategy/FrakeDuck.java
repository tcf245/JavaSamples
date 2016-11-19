package strategy;

/**
 * Created by tcf24 on 2016/11/19.
 */
public class FrakeDuck extends Duck {

    public FrakeDuck() {
        this.setFlyBehavior(new FlyingWhtiNoWay());
        this.setQuackBehavior(new QuackNoWay());
    }

    public void showType() {
        System.out.println("this is a frake Duck..");
    }
}
