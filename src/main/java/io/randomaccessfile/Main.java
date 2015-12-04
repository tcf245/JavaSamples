package io.randomaccessfile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by pc9507 on 2015/12/4.
 *
 * RandomAccessFile类在随机(相对顺序而言)读写等长记录格式的文件时有很大的优势
 * 例如：读取数据库中的某一条记录。
 * 但是它仅限于操作文件，不能访问其他的IO设备，如网络、内存映像等
 *
 * 使用RandomAccessFile读写文件注意以下几点
 * 1.在进行文件操作之前必须打开文件，并制定读写方式(r,w,rw 分别代表读、写、读写)
 * 在用文件对象实例化一个输入/输出类时，就会进行打开文件操作。
 * 2.写入文件必须使用对应的写入方法，一般以write开头
 * 低级文件的写入、数据的写入必须使用与之对应的类型的方法 例如 writeInt()
 * 3.读取文件方法一般以read开头 同样需要注意类型对应
 * 4.读写完成之后关闭文件。对某些文件存取对象来说， close()方法意味着将缓冲区数据写入文件(flush()方法)
 * 如果不关闭 有可能导致数据丢失。
 */
public class Main {
    public static void main(String[] args) {
        Employee[] employees = new Employee[4];
        employees[0] = new Employee("Jack",23);
        employees[1] = new Employee("Lucy",20);
        employees[2] = new Employee("Roseeeeeeeeeeee",22);
        employees[3] = new Employee("Jhon",26);

        String fileName = "lib/random.txt";
        File file = new File(fileName);

        randomWriteFile(employees,file);

        Employee[] ems = randomReadFile(file,4);
        for (Employee e:ems) {
            System.out.println("name: " + e.getName() + "     |  age:" + e.getAge());
        }
    }

    /**
     * 将指定对象数组数据写入文件的方法
     * @param employees
     * @param file
     */
    public static void randomWriteFile(Employee[] employees,File file){
        RandomAccessFile randomAccessFile;
        try {
            randomAccessFile = new RandomAccessFile(file,"rw");
            for (Employee e:employees){
                randomAccessFile.writeChars(e.getName());
                randomAccessFile.writeInt(e.getAge());
            }
            randomAccessFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String readName(RandomAccessFile randomAccessFile) throws IOException {
        char[] name = new char[8];
        for (int i=0;i< name.length ;i++){
            name[i] = randomAccessFile.readChar();
        }
        return new String(name).replace("\0"," ");
    }

    /**
     * 读取文件中数据并返回对象数组
     * @param file
     * @param count
     * @return
     */
    public static Employee[] randomReadFile(File file,int count){
        RandomAccessFile randomAccessFile;
        Employee[] employees = new Employee[count];

        try {
            randomAccessFile = new RandomAccessFile(file,"r");
            for (int i = 0; i < count; i++){
                randomAccessFile.seek(i * Employee.size());
                employees[i] = new Employee(readName(randomAccessFile),randomAccessFile.readInt());
            }
            randomAccessFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
    }

}
