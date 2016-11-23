package wrapper;

/**
 * Created by tcf24 on 2016/11/23.
 */
public class Milk extends Decorator {

    private Decorator decorator = null;

    public Milk(Decorator decorator) {
        this.decorator = decorator;
    }

    @Override
    public void method() {
        decorator.method();
        System.out.println("Milk. ");
    }

    @Override
    public double getPrice() {
        return decorator.getPrice() + 10 ;
    }
}
