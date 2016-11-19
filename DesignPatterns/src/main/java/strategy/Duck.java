package strategy;

/**
 * Created by tcf24 on 2016/11/19.
 */
public abstract class Duck {

    private FlyBehavior flyBehavior;
    private QuackBehavior quackBehavior;

    public Duck() {}

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }

    public void display(){
        showType();
        System.out.println("Flying Behavior is :");
        flyBehavior.Flying();
        System.out.println("Quack Behavior is :");
        quackBehavior.Quack();
    };

    public abstract void showType();

}