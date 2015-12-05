package io.objectinputstream;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc9507 on 2015/12/5.
 *
 * 在流中写如何读取对象时，要使用writeObject() 和readObject()方法
 * 由于读出的对象以Object类型返回，所以必须转化为原来的数据类型才能还原
 *
 * ObjectOutputStream流再写入对象数据时，会加入一个特别的流头(StreamHeader)
 * 目的是为了读出的时候检查这个流头。如果是附加对象的操作，就会出现多个流头
 * 会导致读取数据室发生错误，抛出java.io.StreamCorrupedException异常。
 * 为了解决这个问题，需要重写writeStreamHeader()方法，在附加方式写入文件时，
 * 不再写入流头
 *
 */
public class ObjectMain {
    public static void main(String[] args) {
        File file = new File("lib/object.txt");
        Person[] persons = {new Person("jack",21),new Person("lucy",25)};
        writeObjectsToFile(persons,file);

        List<Person> list = readObjectsFromFile(file);
        for(Person p:list){
            System.out.println(p.getName() + "  " + p.getAge());
        }

        persons = new Person[2];
        persons[0] = new Person("李四",22);
        persons[1] = new Person("王五",20);

        appendObjectToFile(persons,file);
        list = readObjectsFromFile(file);
        for(Person p:list){
            System.out.println(p.getName() + "  " + p.getAge());
        }

    }

    /**
     * 将对象数组写入文件的方法
     * @param persons
     * @param file
     */
    public static void writeObjectsToFile(Person[] persons, File file){
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(file));
            for (Person s:persons){
                oos.writeObject(s);
            }
            oos.close();
            System.out.println("数据写入完成！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从文件中读取文件的方法
     * @param file
     * @return
     */
    public static List<Person> readObjectsFromFile(File file){
        ObjectInputStream ois;
        List<Person> persons = new ArrayList<>();

        try {
            if (!file.exists()){
                throw new FileNotFoundException();
            }
            ois = new ObjectInputStream(new FileInputStream(file));
            while(ois.available() > 0){
                persons.add((Person) ois.readObject());
            }
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return persons;
    }

    /**
     * 以附加形式将对象数据写入文件
     * @param persons
     * @param file
     */
    public static void appendObjectToFile(Person[] persons,File file){
        ObjectOutputStream oos;

        try {
            oos = new ObjectOutputStream(
                    new FileOutputStream(file,true)){
                //附加对象至文件末端，必须重新定义这个方法。
                @Override
                protected void writeStreamHeader() throws IOException {}
            };

            for (Person p:persons){
                oos.writeObject(p);
            }
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
