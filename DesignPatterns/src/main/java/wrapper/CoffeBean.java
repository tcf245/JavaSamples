package wrapper;

/**
 * Created by tcf24 on 2016/11/23.
 */
public class CoffeBean extends Decorator {

    private Decorator decorator = null;

    public CoffeBean(Decorator decorator) {
        this.decorator = decorator;
    }

    @Override
    public void method() {
        decorator.method();
        System.out.println("CoffeBean ..");
    }

    @Override
    public double getPrice() {
        return decorator.getPrice() + 20;
    }
}
