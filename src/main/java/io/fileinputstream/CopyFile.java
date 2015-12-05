package io.fileinputstream;

import java.io.*;

/**
 * Created by pc9507 on 2015/12/4.
 *
 * 当创建一个FileInputStream或者FileOutputStream流实例时
 * 必须指定文件路径和文件名
 * 对象被创建时，流就会打开，用完之后必须关闭流，以释放资源，完成读写操作
 *
 * 注意：FileOutputStream默认是以创建新文件的方式启动流，如果创建的文件存在，则将会被覆盖。
 * 如实例化时指定为附加模式(构造方法的append参数传true)，若文件存在，则直接打开源文件并启动流
 * 写入数据到文件末端，若文件不存在，则新建文件并启动流。
 *
 */
public class CopyFile {
    public static void main(String[] args) {
        FileInputStream fis;
        FileOutputStream fos;
        byte[] buffer = new byte[1024];
        try {
            fis = new FileInputStream(new File("lib/random.txt"));
            fos = new FileOutputStream(new File("lib/random_copy.txt"));

            int length = fis.available();
            System.out.println("复制文件：　" + length +"　字节");
            while(true){
                if (length < 1021){
                    int remain = -1;
                    while((remain = fis.read()) != -1){
                        fos.write(remain);
                    }
                    break;
                }else{
                    fis.read(buffer);
                    fos.write(buffer);
                }
            }
            fis.close();
            fos.close();
            System.out.println("复制完成！");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
