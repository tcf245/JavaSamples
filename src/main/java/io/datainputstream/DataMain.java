package io.datainputstream;

import io.randomaccessfile.Employee;

import java.io.*;

/**
 * Created by pc9507 on 2015/12/4.
 *
 * 使用DataInputStream从文件中读出基本数据时
 * 不需要煞费苦心的判断读出的基本类型数据何时停止
 * 使用readUTF() readInt()等方法可以正确读取数据
 * 而使用DataOutputStream同样可以正确的保存基本数据类型的数据。
 * 它们并未改变InputStream或OutputStream中的方法。只是在实现对应方法时
 * 添加了动态判断数据类型的功能
 */
public class DataMain {
    public static void main(String[] args) {
        String fileName = "lib/random.txt";
        Employee[] employees = new Employee[4];
        employees[0] = new Employee("Jack",23);
        employees[1] = new Employee("Lucy",20);
        employees[2] = new Employee("Roseeeeeeeeeeee",22);
        employees[3] = new Employee("Jhon",26);

        DataInputStream dis;
        DataOutputStream dos;

        try {
            dis = new DataInputStream(new FileInputStream(fileName));
            dos = new DataOutputStream(new FileOutputStream(fileName));

            for (Employee e:employees){
                dos.writeUTF(e.getName());
                dos.writeInt(e.getAge());
            }
            dos.flush();
            dos.close();

            for (int i = 0; i < employees.length; i++) {
                String name = dis.readUTF();
                int age = dis.readInt();
                employees[employees.length - 1 - i] = new Employee(name,age);
            }
            dis.close();
            for (Employee e:employees) {
                System.out.printf("%s\t%d%n",e.getName(),e.getAge());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
