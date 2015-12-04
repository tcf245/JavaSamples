package io.bufferedInputstream;

import java.io.*;

/**
 * Created by pc9507 on 2015/12/4.
 * 缓冲区的使用是因为硬盘文件的存取速度远远低于内存中的数据存取速度。
 * 为了减少对硬盘的访问次数，通常在读入文件时不是以一个个字节来当作单位，
 * 而是读入一定长度的数据。同样写入数据也需要写入一定长度的数据，提高文件读写效率
 *
 * BufferefInputStream的数据成员buf是一个位数组，默认为2048字节
 * 读取数据是会尽量将buf填满，当时用read()方法时，实际上是先读取buf中的数据
 * 而不是直接对数据来源进行读取，当buf中数据不足时
 * 才会调用给定的InputStream.read()方法
 * 从指定的装置中提取数据
 *
 * BufferedOutputStream的数据成员buf是一个位数组，默认为512字节
 * 当使用write()方法写入数据时，会现将数据写入buf中，当buf已满时才会调用给定的outputStream.write()
 * 将数据写入目的地，而不是每次都对文件进行写入操作
 *
 */
public class BufferedMain {
    public static void main(String[] args) {
        byte[] data = new byte[1];
        File srcFile = new File(args[0]);
        File desFile = new File(args[1]);
        BufferedInputStream bis;
        BufferedOutputStream bos;

        try {
            bis = new BufferedInputStream(new FileInputStream(srcFile));
            bos = new BufferedOutputStream(new FileOutputStream(desFile));
            System.out.println("复制文件： " + srcFile.length() + "  字节");

            while (bis.read(data) != -1){ // 先读取buf中的数据，并不用每次都对磁盘文件操作
                bos.write(data);
            }
            bos.flush();
            bis.close();
            bos.close();
            System.out.println("复制完成！");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
