package io.randomaccessfile;

/**
 * Created by pc9507 on 2015/12/4.
 */
public class Employee {
    private String name;
    private int age;
    public static final int LEN = 8;

    public Employee(String name, int age) {
        if (name.length() > LEN){
            name = name.substring(0,8);
        }else{
            while(name.length() < LEN){
                name = name + "\u0000";
            }
        }
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static int size(){
        return 2*8 + 4;
    }
}
