package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

/**
 * Created by pc9507 on 2015/12/18.
 * java.lang.reflect 包中有三个类Field、Method和Constructor分别用于描述类的域、方法和构造器。
 * 这三个类都有一个getName()用来返回相应条目的名称。Field类有一个getType()方法，用来返回描述域
 * 所属类型的的Class类型对象。这三个类还有一个方法叫做getModifiers()，他将返回一个整形数值，用不同
 * 的位置开关来描述这些修饰符的使用情况，可以利用Modifier.toString()方法将其打印出来
 *
 * Class类中的getXXX方法将返回类支持的public域，方法和构造函数，包括超类的共有成员。
 * getDeclareXXX方法将反悔类中声明的所有域、方法和构造函数，包括私有的和受保护的成员，但是不包括超类成员。
 *
 */
public class ReflectionTest {
    public static void main(String[] args) {
        String name;
        if (args.length > 0){
            name = args[0];
        }
        else{
            Scanner in  = new Scanner(System.in);
            System.out.println("Enter class name:(e.g. java.util.Date)");
            name = in.next();
        }

        try {
            Class cl = Class.forName(name);
            Class superCl = cl.getSuperclass();
            System.out.print("class" + name);
            if (superCl != null && superCl != Object.class){
                System.out.print("extends" + superCl);
            }
            System.out.print("\n{\n");
            printConstructors(cl);
            System.out.println();
            printMethods(cl);
            System.out.println();
            printFields(cl);
            System.out.println("}");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    /**
     * 打印出所有的构造函数
     * @param cl
     */
    public static void printConstructors(Class cl){
        Constructor[] constructors = cl.getDeclaredConstructors();
        for (Constructor c:constructors){
            System.out.print("    " + Modifier.toString(c.getModifiers()));
            System.out.print(" " + c.getName() + "(");
            //打印参数表
            Class[] paramTypes = c.getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++){
                if (i > 0){
                    System.out.print(",");
                }
                System.out.print(paramTypes[i].toString());
            }
            System.out.println(");");
        }
    }

    /**
     * 打印出所有的方法
     * @param cl
     */
    public static void printMethods(Class cl){
        Method[] methods = cl.getDeclaredMethods();
        for (Method m:methods){
            Class returnType = m.getReturnType();
            System.out.print("    " + Modifier.toString(m.getModifiers()));
            System.out.print(" " + returnType + " " + m.getName() + "(");
            //打印参数表
            Class[] paramTypes = m.getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++){
                if (i > 0){
                    System.out.print(",");
                }
                System.out.print(paramTypes[i].toString());
            }
            System.out.println(");");
        }

    }

    /**
     * Print all fields of Class cl
     * @param cl
     */
    public static void printFields(Class cl){
        Field[] fields = cl.getDeclaredFields();
        for (Field f:fields){
            System.out.print("    " + Modifier.toString(f.getModifiers()));
            System.out.println(" " + f.getType() + " " + f.getName() + ";" );
        }
    }

}
