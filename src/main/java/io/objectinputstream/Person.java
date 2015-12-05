package io.objectinputstream;

import java.io.Serializable;

/**
 * Created by pc9507 on 2015/12/5.
 * 如果想要实现直接存储数据对象，在定义类的时候必须实现java.io.Serializable接口
 * 这是一个标记接口，没有规定必须实现的方法
 *
 * 对象序列化是指对象能够将自己的状态信息数据保存下来的特性。对象序列化的目的是使得程序中的
 * 一个对象存储，或者网络传输之后，另一个程序可以将对象还原。
 */
public class Person implements Serializable{
    private String name;
    private int age;

    public Person(String name, int age) {
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
}
