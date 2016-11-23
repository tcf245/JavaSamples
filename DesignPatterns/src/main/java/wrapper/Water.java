package wrapper;

/**
 * Created by tcf24 on 2016/11/23.
 */
public class Water extends Decorator {
    private Decorator decorator = null;

    public Water(Decorator decorator) {
        this.decorator = decorator;
    }

    @Override
    public void method() {
        decorator.method();
        System.out.println("Water .");
    }

    @Override
    public double getPrice() {
        return decorator.getPrice() + 1;
    }
}
