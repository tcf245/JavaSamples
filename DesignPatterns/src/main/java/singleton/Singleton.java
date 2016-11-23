package singleton;

/**
 * Created by tcf24 on 2016/11/23.
 */
public class Singleton {
    private static Singleton instance = null;

    private Singleton() {
    }

    public Singleton getInstance(){
        return SingletonFactory.INSTANCE;
    }

    public static class SingletonFactory{
        public static Singleton INSTANCE = new Singleton();
    }

}
