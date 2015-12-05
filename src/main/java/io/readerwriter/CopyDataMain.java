package io.readerwriter;

import java.io.*;

/**
 * Created by pc9507 on 2015/12/5.
 *
 * 字符输入流InputStreamReader和输出流OutputStreamWriter分别是
 * Reader和Writer的子类，可以用InputStream和OutputStream进行处理
 * 即按照字符为基本单位进行读写操作，实现了字符流和字节流之间的转换
 * 字符流操作比字节流效率更高
 */
public class CopyDataMain {

    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("lib/random.txt");
            InputStreamReader isr = new InputStreamReader(fis);

            FileOutputStream fos = new FileOutputStream("lib/random.bak");
            OutputStreamWriter osw = new OutputStreamWriter(fos);

            int ch = 0;
            while((ch = isr.read()) != -1){
                System.out.print((char) ch);
                osw.write(ch);
            }
            System.out.println();
            isr.close();
            osw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
